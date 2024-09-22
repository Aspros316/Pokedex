package com.example.pokedex.data.repository.retrofit

import com.example.pokedex.data.repository.model.RemotePokemonTypes
import retrofit2.http.GET

interface PokedexWebService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(): RemotePokemonTypes

}