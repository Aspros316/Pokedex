package com.example.pokedex.data.repository.retrofit

import com.example.pokedex.data.repository.model.POKEMON_NUMBER
import com.example.pokedex.data.repository.model.RemotePokemonResult
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexWebService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<RemotePokemonTypes>


    @GET("api/v2/pokemon/{$POKEMON_NUMBER}")
    suspend fun getPokemonDetail(@Path(POKEMON_NUMBER) pokemonNumber: String): RemotePokemonResult

}