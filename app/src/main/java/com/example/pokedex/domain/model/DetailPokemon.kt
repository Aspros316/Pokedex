package com.example.pokedex.domain.model

import com.example.pokedex.data.repository.model.RemoteSprites

data class DetailPokemon(
    val remoteAbilities: List<Abilitie>,
    val remoteSprites: RemoteSprites,
)
