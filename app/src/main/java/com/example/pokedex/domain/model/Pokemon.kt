package com.example.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val url: String,
    val artwork: String
){
    fun getUrlNumber(): String {
        val urlParts = url.split("/")
        return Integer.parseInt(urlParts.get(urlParts.size - 2)).toString()
    }
}


