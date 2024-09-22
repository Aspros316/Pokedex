package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.domain.GetAllPokemonUseCase
import com.example.pokedex.utils.sealed.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    ) : ViewModel() {

    private val _createQrStateFlow: MutableStateFlow<Result<RemotePokemonTypes>> =
        MutableStateFlow(Result.OnLoading())
    val createQrFlow = _createQrStateFlow.asStateFlow()


    fun getAllPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllPokemonUseCase.execute().collect { _createQrStateFlow.emit(it) }
        }
    }

}

