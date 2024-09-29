package com.example.pokedex.data.source

import com.example.pokedex.data.cache.model.PokemonTable
import kotlinx.coroutines.flow.Flow

interface PokemonDb {
    suspend fun savePokemonFavorite(pokemonTable: PokemonTable)
    suspend fun getPokemonFavorite(pokemonId: Int): Flow<PokemonTable>
    suspend fun deletePokemon(pokemonId: Int)
    suspend fun getAllFavorite(): Flow<List<PokemonTable>>
}