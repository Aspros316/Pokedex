package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.data.cache.model.PokemonDb
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.retrofit.PokedexWebService
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.data.source.PokemonCache
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val webService: PokedexWebService,
    private val pokemonCache: PokemonCache
) : PokedexRepository {

    override suspend fun getListPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                PokedexPagingDataSource(webService)
            }
        ).flow
    }

    override suspend fun getPokemonDetail(id: String): RemoteDetailPokemon =
        webService.getPokemonDetail(id)

    override suspend fun savePokemonFavorite(pokemonDb: PokemonDb) {
       return pokemonCache.savePokemon(pokemonDb)
    }
}