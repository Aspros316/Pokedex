package com.example.pokedex.data.mapper

import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.rocketreserver.TypesPokemonQuery


fun TypesPokemonQuery.Pokemons.toRemote()  = RemotePokemonTypes(
    remotePokemonTypes = results?.map {
        it?.toRemoteListPokemon() ?: RemotePokemon(
            id = 0,
            name = "",
            image = "",
            url = ""
        )
    }?: listOf()

)

fun TypesPokemonQuery.Result.toRemoteListPokemon() = RemotePokemon(
    id = id ?: 0 ,
    name = name.orEmpty(),
    image = image.orEmpty(),
    url = url.orEmpty()
)


/*fun List<TypesPokemonQuery.Result?>.toRemote(): List<RemotePokemon> = map {
    it!!.toRemoteListPokemon()
}


fun TypesPokemonQuery.Result.toRemoteListPokemon() = RemotePokemon(
    id = id ?: 0 ,
    name = name.orEmpty(),
    image = image.orEmpty(),
    url = url.orEmpty()
)*/



