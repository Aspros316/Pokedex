package com.example.pokedex.di

import com.example.pokedex.data.source.PokedexRepository
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.favorite.GetAllPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.GetPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.SavePokemonFavoriteUseCase
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

    @Provides
    fun bindSavePokemonFavoriteUseCase(remote: PokedexRepository) = SavePokemonFavoriteUseCase(remote)

    @Provides
    fun bindGetPokemonFavoriteUseCase(remote: PokedexRepository) = GetPokemonFavoriteUseCase(remote)

    @Provides
    fun bindGetAllPokemonFavoriteUseCase(remote: PokedexRepository) = GetAllPokemonFavoriteUseCase(remote)

}