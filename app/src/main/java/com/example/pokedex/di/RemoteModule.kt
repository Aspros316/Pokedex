package com.example.pokedex.di

import androidx.paging.PagingSource
import com.example.pokedex.data.repository.PokemonPagingDataSource
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.data.repository.graphql.PokeApi
import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.model.Pokemon
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindPokedexRemote(remote: PokemonRepositoryImpl): PokemonRepository

    companion object {
        @Provides
        fun provideListRemotePagingSource(
            api: PokeApi,
        ): PokemonPagingDataSource {
            return PokemonPagingDataSource(pokeApi = api)
        }
    }

}

