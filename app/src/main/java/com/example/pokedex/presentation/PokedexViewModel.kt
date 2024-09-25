package com.example.pokedex.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.utils.network.ExecutionThread
import com.example.pokedex.utils.sealed.Result
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
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val dispatcher: ExecutionThread,

    ) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PagingData<Pokemon>> =
        MutableStateFlow(value = PagingData.empty())
    val pokemonListState = _pokemonListState.asStateFlow()

    private val _detailStateFlow: MutableStateFlow<Result<DetailPokemon>> =
        MutableStateFlow(Result.OnLoading())
    val detailStateFlow = _detailStateFlow.asStateFlow()

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

    fun getPokemonDetail(id: String) {
        viewModelScope.launch(dispatcher.ioThread) {
            getPokemonDetailUseCase.execute(id)
                .map {
                    Log.i("result", "resultado ${it}")
                    _detailStateFlow.emit(it) }.stateIn(this)
        }
    }

}
