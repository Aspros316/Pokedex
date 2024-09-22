package com.example.pokedex.data.source

import com.example.pokedex.data.repository.model.RemotePokemonTypes

interface PokedexRepository {

    suspend fun getAllPokemon(): RemotePokemonTypes

}