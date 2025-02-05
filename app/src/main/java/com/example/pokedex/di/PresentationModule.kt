package com.example.pokedex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.presentation.PokemonDetailViewModel
import com.example.pokedex.presentation.PokemonListViewModel
import com.example.pokedex.presentation.PokemonViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Module
@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    abstract fun bindPokemonViewModel(viewModel: PokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    abstract fun bindPokemonListViewModel(viewModel: PokemonListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    abstract fun bindPokemonDetailViewModel(viewModel: PokemonDetailViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}