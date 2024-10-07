package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonTypes (
    @SerializedName("results") val remotePokemonTypes: List<RemotePokemon>?
)