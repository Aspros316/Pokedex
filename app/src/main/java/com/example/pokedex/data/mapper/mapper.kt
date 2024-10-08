package com.example.pokedex.data.mapper

import com.example.pokedex.data.repository.model.RemoteAbilitie
import com.example.pokedex.data.repository.model.RemoteAbility
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.model.RemoteMove
import com.example.pokedex.data.repository.model.RemoteMoves
import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.pokedex.data.repository.model.RemoteSprites
import com.example.pokedex.data.repository.model.RemoteType
import com.example.pokedex.data.repository.model.RemoteTypes
import com.example.pokedex.domain.model.Abilities
import com.example.pokedex.domain.model.Ability
import com.example.pokedex.domain.model.DetailPokemon
import com.example.rocketreserver.DetailPokemonQuery
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




fun DetailPokemonQuery.Pokemon.toDetailPokemon() = RemoteDetailPokemon(
    id = id ?: 0,
    name = name.orEmpty(),
    remoteAbilities = abilities?.toAbilities()?: listOf() ,
    remoteMoves = moves?.toMoves().orEmpty(),
    remoteTypes = types?.toTypes().orEmpty(),
    remoteSprites = sprites?.toRemote()
    )


fun DetailPokemonQuery.Sprites.toRemote() = RemoteSprites(
   frontDefault = front_default
)

fun List<DetailPokemonQuery.Type?>.toTypes() = map { types ->
    types?.type?.toType()
}

private fun DetailPokemonQuery.Type1.toType() = RemoteTypes(
    remoteTypes = RemoteType(
        name = name ?: ""
    )
)

fun List<DetailPokemonQuery.Ability?>.toAbilities() = map { abilities ->
    abilities?.ability?.toAbilitie()
}

private fun DetailPokemonQuery.Ability1.toAbilitie() = RemoteAbilitie(
    ability = RemoteAbility(
        name = name ?: ""
    )
)

fun List<DetailPokemonQuery.Move?>.toMoves() = map { moves ->
    moves?.move?.toMove()
}

private fun DetailPokemonQuery.Move1.toMove() = RemoteMoves(
    moves = RemoteMove(
        name = name ?: ""
    )
)




