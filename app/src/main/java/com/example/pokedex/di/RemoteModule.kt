package com.example.pokedex.di

import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.data.source.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindPokedexRemote(remote: PokemonRepositoryImpl): PokemonRepository

}