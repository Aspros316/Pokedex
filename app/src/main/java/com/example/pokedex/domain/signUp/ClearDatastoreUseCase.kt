package com.example.pokedex.domain.signUp

import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.utils.usecase.BaseUseCase
import javax.inject.Inject

class ClearDatastoreUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend fun execute() {
        return repository.clearDatastore()
    }
}