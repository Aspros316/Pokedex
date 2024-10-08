package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteTypes(
    @SerializedName("types") val remoteTypes: RemoteType?
)