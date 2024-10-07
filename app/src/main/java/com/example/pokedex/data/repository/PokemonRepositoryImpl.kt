package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.repository.graphql.PokeApi
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.retrofit.PokemonWebService
import com.example.pokedex.data.source.PokemonCache
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.ui.model.SignUpCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val webService: PokemonWebService,
    private val cache: PokemonCache,
    private val pokeApi: PokeApi,
) : PokemonRepository {

    override suspend fun getListPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                PokemonPagingDataSource(pokeApi)
            }
        ).flow
    }

    override suspend fun getPokemonDetail(pokemonId: String): RemoteDetailPokemon =
        webService.getPokemonDetail(pokemonId)

    override suspend fun savePokemonFavorite(pokemonTable: PokemonTable) = runBlocking {
        cache.savePokemonFavorite(pokemonTable)
    }

    override suspend fun getPokemonFavorite(pokemonId: Int): Flow<PokemonTable> {
        return cache.getPokemonFavorite(pokemonId)
    }

    override suspend fun deletePokemon(pokemonId: Int) = runBlocking {
        cache.deletePokemon(pokemonId)
    }


    override suspend fun getAllPokemonFavorite(): Flow<List<PokemonTable>> {
        return cache.getAllFavorite()
    }

    override suspend fun saveSignUp(username: String, password: String, isValid: Boolean) = runBlocking {
        cache.saveSignUp(username, password, isValid)
    }

    override suspend fun getSignUp(): Flow<SignUpCredentials> = runBlocking {
        cache.getSignUp()
    }

    override suspend fun clearDatastore() {
        cache.clearDataStore()
    }
}