package com.example.pokedex.data.repository

import android.util.Log
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.pokedex.data.repository.retrofit.PokedexWebService
import com.example.pokedex.data.source.PokedexRepository
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val webService: PokedexWebService
) : PokedexRepository {
    override suspend fun getAllPokemon(): RemotePokemonTypes {
        val algo = webService.getAllPokemon()
        Log.i("return", "$algo")
        return webService.getAllPokemon()
    }
}