package com.example.pokedex.di

import com.example.pokedex.data.source.PokemonRepository
import com.example.pokedex.domain.detail.GetPokemonDetailUseCase
import com.example.pokedex.domain.favorite.GetAllPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.GetPokemonFavoriteUseCase
import com.example.pokedex.domain.favorite.SavePokemonFavoriteUseCase
import com.example.pokedex.domain.list.GetListPokemonUseCase
import com.example.pokedex.domain.signUp.ClearDatastoreUseCase
import com.example.pokedex.domain.signUp.GetPokemonSignUpUseCase
import com.example.pokedex.domain.signUp.SavePokemonSignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun bindAllPokemonUseCase(remote: PokemonRepository) = GetListPokemonUseCase(remote)

    @Provides
    fun bindDetailPokemonUseCase(remote: PokemonRepository) = GetPokemonDetailUseCase(remote)

    @Provides
    fun bindSavePokemonFavoriteUseCase(remote: PokemonRepository) =
        SavePokemonFavoriteUseCase(remote)

    @Provides
    fun bindGetPokemonFavoriteUseCase(remote: PokemonRepository) = GetPokemonFavoriteUseCase(remote)

    @Provides
    fun bindGetAllPokemonFavoriteUseCase(remote: PokemonRepository) = GetAllPokemonFavoriteUseCase(remote)

    @Provides
    fun bindSaveSignUpUseCase(remote: PokemonRepository) = SavePokemonSignUpUseCase(remote)

    @Provides
    fun bindGetSignUpUseCase(remote: PokemonRepository) = GetPokemonSignUpUseCase(remote)


    @Provides
    fun bindClearDatastoreUseCase(remote: PokemonRepository) = ClearDatastoreUseCase(remote)

}


