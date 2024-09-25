package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName
import com.example.pokedex.data.repository.model.SPRITES


data class RemotePokemonResult(
    @SerializedName(SPRITES) val remoteSprites: RemoteSprites?,
)