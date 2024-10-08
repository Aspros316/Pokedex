package com.example.pokedex.domain.favorite

import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.utils.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonFavoriteUseCase  @Inject constructor(
    private val repository: PokemonRepository
) : BaseUseCase<Int, Flow<PokemonTable>> {

    override suspend fun execute(input: Int): Flow<PokemonTable> {
        return repository.getPokemonFavorite(input)
    }
}