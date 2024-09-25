package com.example.pokedex.di

import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.list.GetListPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
  class UseCaseModule {

    @Provides
     fun bindAllPokemonUseCase(remote: PokedexRepository) = GetListPokemonUseCase(remote)

    @Provides
    fun bindDetailPokemonUseCase(remote: PokedexRepository) = GetPokemonDetailUseCase(remote)
}