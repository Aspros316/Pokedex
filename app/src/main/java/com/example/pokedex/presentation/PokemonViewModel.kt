package com.example.pokedex.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.domain.favorite.DeletePokemonFavoriteUseCase
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.favorite.GetAllPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.GetPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.SavePokemonFavoriteUseCase
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.signUp.ClearDatastoreUseCase
import com.example.pokedex.domain.signUp.GetPokemonSignUpUseCase
import com.example.pokedex.domain.signUp.SavePokemonSignUpUseCase
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.ui.navigation.PokemonUiEvent
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
class PokemonViewModel @Inject constructor(
    private val getListPokemonUseCase: GetListPokemonUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val savePokemonFavoriteUseCase: SavePokemonFavoriteUseCase,
    private val getPokemonFavoriteUseCase: GetPokemonFavoriteUseCase,
    private val deletePokemonFavoriteUseCase: DeletePokemonFavoriteUseCase,
    private val getAllPokemonFavoriteUseCase: GetAllPokemonFavoriteUseCase,
    private val savePokemonSignUpUseCase: SavePokemonSignUpUseCase,
    private val getPokemonSignUpUseCase: GetPokemonSignUpUseCase,
    private val clearDatastoreUseCase: ClearDatastoreUseCase,
    private val dispatcher: ExecutionThread,

    ) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PagingData<Pokemon>> =
        MutableStateFlow(value = PagingData.empty())
    val pokemonListState = _pokemonListState.asStateFlow()

    private val _detailStateFlow: MutableStateFlow<Result<DetailPokemon>> =
        MutableStateFlow(Result.OnLoading())
    val detailStateFlow = _detailStateFlow.asStateFlow()

    private val _getFavoriteFlow: MutableStateFlow<PokemonTable?> =
        MutableStateFlow(null)
    val getFavoriteFlow = _getFavoriteFlow.asStateFlow()

    private val _favoriteAllFlow : MutableStateFlow<List<PokemonTable>> =
        MutableStateFlow(emptyList())
    val favoriteAllFlow = _favoriteAllFlow.asStateFlow()

    private val _getSignUpFlow : MutableStateFlow<SignUpCredentials> =
        MutableStateFlow(SignUpCredentials())
    val getSignUpFlow = _getSignUpFlow.asStateFlow()

    init {
        getSignUp()
    }

    fun onEvent(event: PokemonUiEvent) {
        when (event) {
            PokemonUiEvent.Navigate -> {
                getListPokemon()
            }

            is PokemonUiEvent.Paginate -> {
                getAllPokemonFavorite()
            }
        }
    }


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
            getPokemonFavoriteUseCase.execute(id).collect{ favorite ->
                _getFavoriteFlow.value = favorite
            }
        }
    }

    fun deletePokemonFavorite(pokemonId: Int) {
        viewModelScope.launch(dispatcher.ioThread) {
            deletePokemonFavoriteUseCase.execute(pokemonId)
        }
    }

    fun getAllPokemonFavorite() {
        viewModelScope.launch(dispatcher.ioThread) {
            getAllPokemonFavoriteUseCase.execute().collect{ favorite ->
                _favoriteAllFlow.value = favorite
            }
        }
    }

    fun saveSignUp(parameter: SignUpCredentials) {
        viewModelScope.launch(dispatcher.ioThread) {
            savePokemonSignUpUseCase.execute(parameter)
        }
    }

    fun getSignUp() {
        viewModelScope.launch(dispatcher.ioThread) {
            getPokemonSignUpUseCase.execute(null).map{ credentials ->
                _getSignUpFlow.value = credentials
            }.stateIn(this)
        }
    }

    fun clearDatastore(){
        viewModelScope.launch(dispatcher.ioThread) {
            clearDatastoreUseCase.execute()
        }
    }

}

