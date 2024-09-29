package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.retrofit.PokedexWebService
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val webService: PokedexWebService,
    private val pokemonDb: com.example.pokedex.data.source.PokemonDb
) : PokedexRepository {

    override suspend fun getListPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                PokemonPagingDataSource(webService)
            }
        ).flow
    }

    override suspend fun getPokemonDetail(pokemonId: String): RemoteDetailPokemon =
        webService.getPokemonDetail(pokemonId)

    override suspend fun savePokemonFavorite(pokemonTable: com.example.pokedex.data.cache.model.PokemonTable) {
       return this.pokemonDb.savePokemonFavorite(pokemonTable)
    }

    override suspend fun getPokemonFavorite(pokemonId: Int): Flow<com.example.pokedex.data.cache.model.PokemonTable> {
        return pokemonDb.getPokemonFavorite(pokemonId)
    }

    override suspend fun deletePokemon(pokemonId: Int) {
        return pokemonDb.deletePokemon(pokemonId)
    }

    override suspend fun getAllPokemonFavorite(): Flow<List<com.example.pokedex.data.cache.model.PokemonTable>> {
        return pokemonDb.getAllFavorite()
    }
}