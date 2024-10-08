package com.example.pokedex.domain.detail.mapper

import com.example.pokedex.data.repository.model.RemoteAbilitie
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.model.RemoteMoves
import com.example.pokedex.data.repository.model.RemoteSprites
import com.example.pokedex.data.repository.model.RemoteTypes
import com.example.pokedex.domain.model.Abilities
import com.example.pokedex.domain.model.Ability
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Move
import com.example.pokedex.domain.model.Moves
import com.example.pokedex.domain.model.Sprite
import com.example.pokedex.domain.model.Type
import com.example.pokedex.domain.model.Types

fun RemoteDetailPokemon.toDetailPokemon() = DetailPokemon(
    id = id ?: 0,
    name = name.orEmpty(),
    abilities = remoteAbilities.map { it!!.toAbilities() } ,
    moves = remoteMoves.map { it!!.toMoves()  },
    types = remoteTypes.map { it!!.toTypes() },
    sprite = Sprite(remoteSprites?.frontDefault?: "")
)

private fun RemoteTypes.toTypes() = Types(
    type = Type(
        name = remoteTypes?.name.orEmpty()
    )
)


private fun RemoteMoves.toMoves() = Moves(
    move = Move(
        name = moves?.name.orEmpty()
    )
)

private fun RemoteAbilitie.toAbilities() = Abilities(
    ability = Ability(
         name = ability?.name.orEmpty()
    )
)
