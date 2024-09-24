package com.example.pokedex.data.source

import androidx.paging.PagingData
import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokedexRepository {

    suspend fun getListPokemon(): Flow<PagingData<Pokemon>>
}