package com.example.pokedex.domain.signUp

import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.utils.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonSignUpUseCase   @Inject constructor(
    private val repository: PokemonRepository
) : BaseUseCase<Nothing?, Flow<SignUpCredentials>> {

    override suspend fun execute(input: Nothing?): Flow<SignUpCredentials> {
        return repository.getSignUp()
    }
}