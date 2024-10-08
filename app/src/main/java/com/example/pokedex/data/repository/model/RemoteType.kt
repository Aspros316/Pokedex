package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteType(
    @SerializedName("name") val name: String?
)
