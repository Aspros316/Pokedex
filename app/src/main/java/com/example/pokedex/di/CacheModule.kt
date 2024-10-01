package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.cache.PokemonCacheImpl
import com.example.pokedex.data.cache.database.PokemonDatabase
import com.example.pokedex.data.cache.database.config.POKEMON_TABLE
import com.example.pokedex.data.source.PokemonCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindPokemonCache(cache: PokemonCacheImpl): PokemonCache

    companion object {

        @Provides
        fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()

        @Provides
        fun provideAppDatabase(appContext: Context): PokemonDatabase {
            return Room
                .databaseBuilder(appContext, PokemonDatabase::class.java, POKEMON_TABLE)
                .build()
        }
    }
}