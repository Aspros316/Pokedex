package com.example.pokedex.data.cache

import com.example.pokedex.data.cache.database.dao.PokemonDao
import com.example.pokedex.data.cache.datastore.PokemonDataStore
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonCache
import com.example.pokedex.ui.model.SignUpCredentials
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonCacheImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val dataStore: PokemonDataStore
): PokemonCache {

    override suspend fun savePokemonFavorite(pokemonTable: PokemonTable) {
        return pokemonDao.insertPokemon(pokemonTable)
    }

    override suspend fun getPokemonFavorite(pokemonId: Int): Flow<PokemonTable> {
       return  pokemonDao.getPokemon(pokemonId)
    }

    override suspend fun deletePokemon(pokemonId: Int) {
        return pokemonDao.deletePokemon(pokemonId)
    }

    override suspend fun getAllFavorite(): Flow<List<PokemonTable>> {
        return pokemonDao.getAllFavorite()
    }

    override suspend fun saveSignUp(username: String, password: String, isValid: Boolean) {
        dataStore.saveSignUp(username, password, isValid)
    }

    override fun getSignUp(): Flow<SignUpCredentials> =
        dataStore.getSignUp()

    override suspend fun clearDataStore() {
        dataStore.clearDatastore()
    }
}