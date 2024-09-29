package com.example.pokedex.domain.detail.mapper

import com.example.pokedex.data.cache.model.AbilitieCache
import com.example.pokedex.data.cache.model.AbilityCache
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.repository.model.RemoteAbilitie
import com.example.pokedex.data.repository.model.RemoteDetailPokemon
import com.example.pokedex.data.repository.model.RemoteSprites
import com.example.pokedex.domain.model.Abilitie
import com.example.pokedex.domain.model.Ability
import com.example.pokedex.domain.model.DetailPokemon

fun RemoteDetailPokemon.toDetailPokemon() = DetailPokemon(
    id = id ?: 0,
    name = name.orEmpty(),
    abilities = remoteAbilities?.toAbilities().orEmpty(),
    sprites = RemoteSprites(
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

/*fun DetailPokemon.toPokemonDb() = PokemonTable(
    id = id,
    pokemonName = name,
    abilitie = abilities?.toAbilitieDb().orEmpty(),
    image = sprites.frontDefault.orEmpty(),
    isFavorite = false
)


fun List<Abilitie>.toAbilitieDb() = map { detailPokemon ->
    detailPokemon.toAbilitieDb()
}


private fun Abilitie.toAbilitieDb() = AbilitieCache(
    ability = AbilityCache(
        name = ability.name
    )
)*/

/*
fun DetailPokemon.toCache() = PokemonDb(
    abilitie = abilities.toAbilitiesDb(),
    image = sprites.frontDefault
)


fun List<RemoteAbilitie>.toAbilitiesDb() = map { cache ->
    cache.toCacheAbilitie()
}

private fun RemoteAbilitie.toCacheAbilitie() = Abilitie(
    ability = Ability(
        name = ability?.name.orEmpty()
    )
)*/
