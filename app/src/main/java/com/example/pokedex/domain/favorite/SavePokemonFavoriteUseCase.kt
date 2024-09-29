package com.example.pokedex.domain.favorite

import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.utils.usecase.BaseUseCase
import javax.inject.Inject

class SavePokemonFavoriteUseCase @Inject constructor(
    private val repository: PokedexRepository
) : BaseUseCase<PokemonTable, Any?> {

    override suspend fun execute(input: PokemonTable): Any {
        return repository.savePokemonFavorite(input)
    }
}