package com.example.pokedex.domain.detail

import com.example.pokedex.data.cache.model.PokemonDb
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.utils.usecase.RequestUseCase
import javax.inject.Inject

class SavePokemonFavoriteUseCase @Inject constructor(
    private val repository: PokedexRepository
) : RequestUseCase<PokemonDb, Any?>() {

    override suspend fun executeOnBackground(params: PokemonDb?) {
        check(params != null) { throw IllegalArgumentException() }
        return repository.savePokemonFavorite(params)
    }
}