package com.example.pokedex.domain.detail.mapper

import com.example.pokedex.data.repository.model.RemoteAbilitie
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.pokedex.data.repository.model.RemoteSprites
import com.example.pokedex.domain.model.Abilitie
import com.example.pokedex.domain.model.Ability
import com.example.pokedex.domain.model.DetailPokemon

fun RemoteDetailPokemon.toDetailPokemon() = DetailPokemon(
    remoteAbilities = remoteAbilities?.toAbilities().orEmpty(),
    remoteSprites = RemoteSprites(
        frontDefault = remoteSprites?.frontDefault.orEmpty()
    )
)

fun List<RemoteAbilitie>.toAbilities() = map { detailPokemon ->
    detailPokemon.toAbilitie()
}

private fun RemoteAbilitie.toAbilitie() = Abilitie(
    ability = Ability(
         name = ability?.name.orEmpty()
    )
)