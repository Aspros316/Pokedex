package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName


data class RemoteDetailPokemon(
    @SerializedName("abilities") val remoteAbilities: List<RemoteAbilitie>?,
    @SerializedName(SPRITES) val remoteSprites: RemoteSprites?,
)