package com.example.pokedex.data.source

import com.example.pokedex.data.cache.model.PokemonDb

interface PokemonCache {
    suspend fun savePokemon(pokemonDb: PokemonDb)
}