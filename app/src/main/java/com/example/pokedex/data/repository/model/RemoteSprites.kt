package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName
import com.example.pokedex.data.repository.model.IMAGE_URL

data class RemoteSprites(
    @SerializedName(IMAGE_URL) val frontDefault: String?,
)
