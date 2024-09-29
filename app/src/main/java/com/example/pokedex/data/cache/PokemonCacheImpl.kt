package com.example.pokedex.data.cache

import com.example.pokedex.data.cache.database.dao.PokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonCacheImpl @Inject constructor(
    private val pokemonDao: PokemonDao
): com.example.pokedex.data.source.PokemonDb {

    override suspend fun savePokemonFavorite(pokemonTable: com.example.pokedex.data.cache.model.PokemonTable) {
        return pokemonDao.insertPokemon(pokemonTable)
    }

    override suspend fun getPokemonFavorite(pokemonId: Int): Flow<com.example.pokedex.data.cache.model.PokemonTable> {
       return  pokemonDao.getPokemon(pokemonId)
    }

    override suspend fun deletePokemon(pokemonId: Int) {
        return pokemonDao.deletePokemon(pokemonId)
    }

    override suspend fun getAllFavorite(): Flow<List<com.example.pokedex.data.cache.model.PokemonTable>> {
        return pokemonDao.getAllFavorite()
    }

}