package com.example.pokedex.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.HttpInterceptor
import com.apollographql.apollo.network.okHttpClient
import com.example.pokedex.data.repository.retrofit.PokemonWebService
import com.example.pokedex.utils.network.ExecutionThread
import com.example.pokedex.utils.network.ExecutionThreadImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {


    @Provides
    fun provideExecutionThread(): ExecutionThread = ExecutionThreadImpl()


    @Provides
    fun provideApiService(retrofit: Retrofit): PokemonWebService =
        retrofit.create(PokemonWebService::class.java)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideApollo(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphql-pokeapi.graphcdn.app")
            .okHttpClient(okHttpClient)
            .build()
    }
}