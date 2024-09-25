package com.example.pokedex.data.source

import androidx.paging.PagingData
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {

    suspend fun getListPokemon(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(id: String): RemoteDetailPokemon

}