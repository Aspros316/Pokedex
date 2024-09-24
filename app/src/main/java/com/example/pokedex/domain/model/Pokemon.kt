package com.example.pokedex.domain.model

data class Pokemon(
    val name: String,
    val url: String,
){
    fun getUrlNumber(): String {
        val urlParts = url.split("/")
        return Integer.parseInt(urlParts.get(urlParts.size - 2)).toString()
    }

    fun getImageUrl() :String {
        return  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${getUrlNumber()}.png"
    }
}


