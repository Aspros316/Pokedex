package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.favorite.DeletePokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.GetPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.SavePokemonFavoriteUseCase
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.utils.network.ExecutionThread
import com.example.pokedex.utils.sealed.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getPokemonFavoriteUseCase: GetPokemonFavoriteUseCase,
    private val pokemonRepository: PokemonRepository,
    private val savePokemonFavoriteUseCase: SavePokemonFavoriteUseCase,
    private val deletePokemonFavoriteUseCase: DeletePokemonFavoriteUseCase,
    private val dispatcher: ExecutionThread
    ) : ViewModel(){

    private val _detailStateFlow: MutableStateFlow<Result<DetailPokemon>> =
        MutableStateFlow(Result.OnLoading())
    val detailStateFlow = _detailStateFlow.asStateFlow()

    private val _getFavoriteFlow: MutableStateFlow<PokemonTable?> =
        MutableStateFlow(null)
    val getFavoriteFlow = _getFavoriteFlow.asStateFlow()

    fun getPokemonDetail(name: String) {
        viewModelScope.launch(dispatcher.ioThread) {
            getPokemonDetailUseCase.execute(name)
                .map {
                    _detailStateFlow.emit(it)
                }.stateIn(this)
        }
    }

    fun savePokemonFavorite(parameter: PokemonTable) {
        viewModelScope.launch(dispatcher.ioThread) {
            savePokemonFavoriteUseCase.execute(parameter)
        }
    }

    fun getPokemonFavorite(id: Int) {
        viewModelScope.launch(dispatcher.ioThread) {
            getPokemonFavoriteUseCase.execute(id).collect { favorite ->
                _getFavoriteFlow.value = favorite
            }
        }
    }

    fun savePokemonSeen(seen: Int) {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.savePokemonSeen(seen)
        }
    }

    fun deletePokemonFavorite(pokemonId: Int) {
        viewModelScope.launch(dispatcher.ioThread) {
            deletePokemonFavoriteUseCase.execute(pokemonId)
        }
    }
}