package com.example.pokedex.domain.detail

import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.detail.mapper.toDetailPokemon
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.utils.usecase.RequestUseCase
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) : RequestUseCase<String, DetailPokemon>() {

    override suspend fun executeOnBackground(params: String?): DetailPokemon {
        check(params != null) { throw IllegalArgumentException() }

        return repository.getPokemonDetail(params).toDetailPokemon()
    }
}