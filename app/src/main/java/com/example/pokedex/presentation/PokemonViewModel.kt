package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.favorite.GetAllPokemonFavoriteUseCase
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.signUp.ClearDatastoreUseCase
import com.example.pokedex.domain.signUp.GetPokemonSignUpUseCase
import com.example.pokedex.domain.signUp.SavePokemonSignUpUseCase
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.ui.navigation.PokemonBottomUiEvent
import com.example.pokedex.utils.network.ExecutionThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getAllPokemonFavoriteUseCase: GetAllPokemonFavoriteUseCase,
    private val savePokemonSignUpUseCase: SavePokemonSignUpUseCase,
    private val getPokemonSignUpUseCase: GetPokemonSignUpUseCase,
    private val clearDatastoreUseCase: ClearDatastoreUseCase,
    private val dispatcher: ExecutionThread,
    private val pokemonRepository: PokemonRepository
    ) : ViewModel() {


    private val _favoriteAllFlow: MutableStateFlow<List<PokemonTable>> =
        MutableStateFlow(emptyList())
    val favoriteAllFlow = _favoriteAllFlow.asStateFlow()

    private val _getSignUpFlow: MutableStateFlow<SignUpCredentials> =
        MutableStateFlow(SignUpCredentials())
    val getSignUpFlow = _getSignUpFlow.asStateFlow()

    private val _useTime = MutableStateFlow(0L)
    val useTime: StateFlow<Long> = _useTime.asStateFlow()

    private val _pokemonSeen = MutableStateFlow(0)
    val pokemonSeen: StateFlow<Int> = _pokemonSeen.asStateFlow()

    private val _isViewedOnboarding = MutableStateFlow(false)
    val isViewedOnboarding = _isViewedOnboarding.asStateFlow()


    init {
        getSignUp()
    }

/*    fun onEvent(event: PokemonBottomUiEvent) {
        when (event) {
            PokemonBottomUiEvent.Navigate -> {
                getListPokemon()
            }

            is PokemonBottomUiEvent.Paginate -> {
                getAllPokemonFavorite()
            }
        }
    }*/



    fun getAllPokemonFavorite() {
        viewModelScope.launch(dispatcher.ioThread) {
            getAllPokemonFavoriteUseCase.execute().collect { favorite ->
                _favoriteAllFlow.value = favorite
            }
        }
    }

    fun saveSignUp(parameter: SignUpCredentials) {
        viewModelScope.launch(dispatcher.ioThread) {
            savePokemonSignUpUseCase.execute(parameter)
        }
    }

    private fun getSignUp() {
        viewModelScope.launch(dispatcher.ioThread) {
            getPokemonSignUpUseCase.execute(null).map { credentials ->
                _getSignUpFlow.value = credentials
            }.stateIn(this)
        }
    }

    fun clearDatastore() {
        viewModelScope.launch(dispatcher.ioThread) {
            clearDatastoreUseCase.execute()
        }
    }

    fun saveUseTime(useTime: Long) {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.saveUseTime(useTime)
        }
    }

    fun getUseTime() {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.getUseTime().map { useTime ->
                _useTime.value = useTime
            }.stateIn(this)
        }
    }

    fun getPokemonSeen () {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.getPokemonSeen().map { pokemonSeen ->
                _pokemonSeen.value = pokemonSeen
            }.stateIn(this)
        }
    }

    fun storeViewedOnboarding() {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.storeViewedOnboarding()
        }
    }

    fun getIsViewedOnboarding () {
        viewModelScope.launch(dispatcher.ioThread) {
            pokemonRepository.isViewedOnboarding().map { onboardingSeen ->
                println("visto ${onboardingSeen}")
                _isViewedOnboarding.value = onboardingSeen
            }.stateIn(this)
        }
    }
}
