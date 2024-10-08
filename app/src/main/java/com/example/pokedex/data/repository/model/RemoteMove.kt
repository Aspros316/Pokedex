package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteMove(
    @SerializedName("name") val name: String?
)
