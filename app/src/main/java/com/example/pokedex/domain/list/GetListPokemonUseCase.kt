package com.example.pokedex.domain.list

import androidx.paging.PagingData
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.utils.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetListPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
): BaseUseCase<Unit, Flow<PagingData<Pokemon>>>{

    override suspend fun execute(input: Unit): Flow<PagingData<Pokemon>> {
        return  repository.getListPokemon()
    }

}



