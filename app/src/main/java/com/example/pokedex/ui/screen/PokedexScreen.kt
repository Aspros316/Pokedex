package com.example.pokedex.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pokedex.presentation.PokedexViewModel

@Composable
fun PokedexScreen() {
    val pokedexViewModel = hiltViewModel<PokedexViewModel>()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { PokemonScreenTopBar() },
    ) { innerPadding ->
        PokemonContent(
            viewModel = pokedexViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun PokemonContent(viewModel: PokedexViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Blue)
            .fillMaxSize()
    ){
        viewModel.getAllPokemon()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreenTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Pokemon App",
                textAlign = TextAlign.Center,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),

        )
}
