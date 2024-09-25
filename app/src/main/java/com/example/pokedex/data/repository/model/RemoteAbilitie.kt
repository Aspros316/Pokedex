package com.example.pokedex.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteAbilitie(
    @SerializedName("ability") val ability: RemoteAbility?,
)
