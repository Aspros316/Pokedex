package com.example.pokedex.data.source

import androidx.paging.PagingData
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.ui.model.SignUpCredentials
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(pokemonId: String): RemoteDetailPokemon
    suspend fun savePokemonFavorite(pokemonTable: PokemonTable)
    suspend fun getPokemonFavorite(pokemonId: Int): Flow<PokemonTable>
    suspend fun deletePokemon(pokemonId: Int)
    suspend fun getAllPokemonFavorite(): Flow<List<PokemonTable>>
    suspend fun saveSignUp(username: String, password: String, isValid: Boolean)
    suspend fun getSignUp(): Flow<SignUpCredentials>
    suspend fun clearDatastore()

}