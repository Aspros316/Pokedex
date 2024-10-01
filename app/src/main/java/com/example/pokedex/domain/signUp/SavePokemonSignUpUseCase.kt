package com.example.pokedex.domain.signUp

import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.utils.usecase.BaseUseCase
import javax.inject.Inject

class SavePokemonSignUpUseCase  @Inject constructor(
    private val repository: PokemonRepository
) : BaseUseCase<SignUpCredentials, Any?> {

    override suspend fun execute(input: SignUpCredentials): Any {
        return repository.saveSignUp(input.username, input.password, input.isValid)
    }
}