package com.example.pokedex.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.composables.NavTopBar

@Composable
fun FavoritePokemonScreen(
    viewModel: PokedexViewModel,
    navigateUp: () -> Unit,
) {

    LaunchedEffect(key1 = Unit, block = { viewModel.getAllPokemonFavorite() })

    val favoriteList = viewModel.favoriteAllFlow.value
    if (favoriteList.isNotEmpty()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NavTopBar(
                    modifier = Modifier,
                    title = "Pokemon",
                    canNavigateBack = true,
                    navigateUp = navigateUp,
                    favoriteClick = {})
            },
        ) { innerPadding ->
            FavoritePokemonContent(Modifier.padding(innerPadding), favoriteList, navigateUp)
        }
    }


}


@Composable
fun FavoritePokemonContent(
    modifier: Modifier = Modifier,
    favoritesList: List<PokemonTable>,
    navigateUp: () -> Unit,

) {




  /*      LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .background(Color.White),

            ) {
            items(favoritesList) { favorite ->


                Text(text = favorite.pokemonName)

            }

        }*/





}