package com.example.pokedex.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.component.Loader
import com.example.pokedex.ui.composables.NavTopBar
import com.example.pokedex.utils.sealed.Result

@Composable
fun DetailPokemonScreen(
    viewModel: PokedexViewModel,
    navigateUp: () -> Unit,
    idPokemon: Int,
    name: String,
) {
    LaunchedEffect(
        key1 = Unit,
        block = {
            viewModel.getPokemonDetail(idPokemon.toString())
        })

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState = produceState<Result<DetailPokemon>>(
        initialValue = Result.OnLoading(),
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.detailStateFlow.collect { value = it }
        }
    }

    DetailPokemonState(uiState.value, navigateUp, idPokemon, name)
}

@Composable
fun DetailPokemonState(
    value: Result<DetailPokemon>,
    navigateUp: () -> Unit,
    idPokemon: Int,
    name: String,
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Pokemon",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
    ) { innerPadding ->

        when (value) {
            is Result.OnLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Loader()
                }
            }

            is Result.OnSuccess -> {
                DetailPokemonContent(
                    modifier = Modifier.padding(innerPadding),
                    name = name,
                    idPokemon = idPokemon
                )
            }

            is Result.OnError -> {}
            is Result.OnEmpty -> TODO()
        }

    }
}


@Composable
fun DetailPokemonContent(modifier: Modifier, name: String, idPokemon: Int) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = name + idPokemon)
    }


}

