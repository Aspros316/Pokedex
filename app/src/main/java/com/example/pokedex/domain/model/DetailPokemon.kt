package com.example.pokedex.domain.model

import com.example.pokedex.data.repository.model.RemoteSprites

data class DetailPokemon(
    val id: Int,
    val name: String,
    val abilities: List<Abilitie>,
    val sprites: RemoteSprites,

)
