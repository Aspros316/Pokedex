package com.example.pokedex.di

import com.example.pokedex.data.repository.PokedexRepositoryImpl
import com.example.pokedex.data.source.PokedexRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindPokedexRemote(remote: PokedexRepositoryImpl): PokedexRepository

}