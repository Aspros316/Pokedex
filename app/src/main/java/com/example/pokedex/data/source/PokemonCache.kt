package com.example.pokedex.data.source

import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.ui.model.SignUpCredentials
import kotlinx.coroutines.flow.Flow

interface PokemonCache {
    suspend fun savePokemonFavorite(pokemonTable: PokemonTable)
    suspend fun getPokemonFavorite(pokemonId: Int): Flow<PokemonTable>
    suspend fun deletePokemon(pokemonId: Int)
    suspend fun getAllFavorite(): Flow<List<PokemonTable>>
    suspend fun saveSignUp(username: String, password: String, isValid: Boolean)
    fun getSignUp(): Flow<SignUpCredentials>
    suspend fun clearDataStore()

}