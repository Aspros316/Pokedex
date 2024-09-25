package com.example.pokedex.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokedex.ui.composables.NavTopBar

@Composable
fun DetailPokemonScreen(
    navigateUp:() -> Unit,
    idPokemon: Int,
    name: String,
) {

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
        DetailContent(
            modifier = Modifier.padding(innerPadding),
            name = name,
            idPokemon = idPokemon
        )
    }


}

@Composable
fun DetailContent(modifier: Modifier, name: String, idPokemon: Int) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = name + idPokemon)
    }


}

