package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName


data class RemoteDetailPokemon(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("abilities") val remoteAbilities: List<RemoteAbilitie>?,
    @SerializedName(SPRITES) val remoteSprites: RemoteSprites?,
)