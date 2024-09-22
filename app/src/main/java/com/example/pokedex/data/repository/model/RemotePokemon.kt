package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemotePokemon(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
)