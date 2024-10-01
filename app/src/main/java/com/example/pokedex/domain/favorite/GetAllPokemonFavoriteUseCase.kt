package com.example.pokedex.domain.favorite

import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.data.source.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokemonFavoriteUseCase  @Inject constructor(
    private val repository: PokemonRepository
)  {
     suspend fun execute(): Flow<List<PokemonTable>> {
        return repository.getAllPokemonFavorite()
    }
}