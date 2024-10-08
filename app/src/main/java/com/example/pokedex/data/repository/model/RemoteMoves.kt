package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteMoves(
    @SerializedName("moves") val moves: RemoteMove?
)
