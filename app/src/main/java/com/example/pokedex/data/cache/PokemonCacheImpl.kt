package com.example.pokedex.data.cache

import com.example.pokedex.data.cache.database.dao.PokemonDao
import com.example.pokedex.data.cache.model.PokemonDb
import com.example.pokedex.data.source.PokemonCache
import javax.inject.Inject

class PokemonCacheImpl @Inject constructor(
    private val pokemonDao: PokemonDao
): PokemonCache {
    override suspend fun savePokemon(pokemonDb: PokemonDb) {
        pokemonDao.insertPokemon(pokemonDb)
    }
}