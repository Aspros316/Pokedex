package com.example.pokedex.domain.model

import com.example.pokedex.data.repository.model.RemoteSprites

data class DetailPokemon(
    val id: Int,
    val name: String,
    val abilities: List<Abilities>,
    val moves: List<Moves>,
    val types: List<Types>,
    val sprite: Sprite,

    )

fun DetailPokemon?.orEmpty(): DetailPokemon = this ?: DetailPokemon(
    id = 0,
    name = "",
    abilities = listOf(),
    moves = listOf(),
    types = listOf(),
    sprite = Sprite("")
)
