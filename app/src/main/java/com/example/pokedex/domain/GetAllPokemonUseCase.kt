package com.example.pokedex.domain

import com.example.pokedex.data.repository.model.RemotePokemonTypes
import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.utils.usecase.RequestUseCase
import javax.inject.Inject


class GetAllPokemonUseCase @Inject constructor(
    private val remote: PokedexRepository
) : RequestUseCase<Nothing, RemotePokemonTypes>() {

    override suspend fun executeOnBackground(params: Nothing?): RemotePokemonTypes {
        return remote.getAllPokemon()
    }
}
