package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.utils.network.ExecutionThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getListPokemonUseCase: GetListPokemonUseCase,
    private val dispatcher: ExecutionThread,
    ) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PagingData<Pokemon>> =
        MutableStateFlow(value = PagingData.empty())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

     fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }


    val pokemonList = searchText
        .combine(_pokemonListState) { text, pokemon ->
            if (text.isBlank()) {
                pokemon
            }
            pokemon.filter { pokemon ->
                pokemon.name.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _pokemonListState.value
        )

    fun getListPokemon() {
        viewModelScope.launch(dispatcher.ioThread) {
            getListPokemonUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .map {
                    _pokemonListState.value = it
                }.stateIn(this)
        }
    }

}