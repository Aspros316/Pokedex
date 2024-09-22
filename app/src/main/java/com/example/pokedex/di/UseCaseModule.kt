package com.example.pokedex.di

import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.domain.GetAllPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
  class UseCaseModule {

    @Provides
     fun bindAllPokemonUseCase(remote: PokedexRepository) = GetAllPokemonUseCase(remote)

}