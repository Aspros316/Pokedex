package com.example.pokedex.di

import com.example.pokedex.data.repository.retrofit.PokemonWebService
import com.example.pokedex.utils.network.ExecutionThread
import com.example.pokedex.utils.network.ExecutionThreadImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {



    @Provides
    fun provideExecutionThread(): ExecutionThread = ExecutionThreadImpl()


    @Provides
    fun provideApiService(retrofit: Retrofit): PokemonWebService = retrofit.create(PokemonWebService::class.java)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}