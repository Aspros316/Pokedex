package com.example.pokedex.domain.favorite

import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.utils.usecase.BaseUseCase
import javax.inject.Inject

class DeletePokemonFavoriteUseCase @Inject constructor(
    private val repository: PokemonRepository
) : BaseUseCase<Int, Any?> {

    override suspend fun execute(input: Int): Any {
        return repository.deletePokemon(input)
    }
}