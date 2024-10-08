package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName


data class RemoteDetailPokemon(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("abilities") val remoteAbilities: List<RemoteAbilitie?>,
    @SerializedName("moves") val remoteMoves: List<RemoteMoves?>,
    @SerializedName("types") val remoteTypes: List<RemoteTypes?>,
    @SerializedName(SPRITES) val remoteSprites: RemoteSprites?,
)