package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.utils.network.ExecutionThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getListPokemonUseCase: GetListPokemonUseCase,
    private val dispatchers: ExecutionThread,

    ) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PagingData<Pokemon>> =
        MutableStateFlow(value = PagingData.empty())
    val pokemonListState = _pokemonListState.asStateFlow()


    init {
        onEvent(HomeEvent.GetHome)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetHome -> {
                getListPokemon()
            }
        }

    }

    private fun getListPokemon() {
        viewModelScope.launch(dispatchers.ioThread) {
            getListPokemonUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .map {
                    _pokemonListState.value = it
                }.stateIn(this)
        }
    }

}

sealed class HomeEvent {
    object GetHome : HomeEvent()
}

