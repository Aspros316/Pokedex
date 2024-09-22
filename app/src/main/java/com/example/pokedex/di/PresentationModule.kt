package com.example.pokedex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.presentation.PokedexViewModel
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
    @ViewModelKey(PokedexViewModel::class)
    abstract fun bindListViewModel(viewModel: PokedexViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}