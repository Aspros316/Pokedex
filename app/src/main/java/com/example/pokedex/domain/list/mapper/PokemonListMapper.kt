package com.example.pokedex.domain.list.mapper

import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.pokedex.domain.model.Pokemon

fun List<RemotePokemon>.toDomain() = map { listPokemon ->
    listPokemon.toPokemon()
}

private fun RemotePokemon.toPokemon() = Pokemon(
    name = name.orEmpty(),
    url = url.orEmpty()
)