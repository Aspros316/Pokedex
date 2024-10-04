package com.example.pokedex.ui.favorite

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pokedex.R
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.ActionItem
import com.example.pokedex.ui.component.BottomNavigationBar
import com.example.pokedex.ui.component.DetailPokemonImage
import com.example.pokedex.ui.component.ListTopAppBar
import com.example.pokedex.ui.component.NavTopBar
import com.example.pokedex.ui.component.PokemonDetailTitle
import com.example.pokedex.ui.component.TopAppBarDropdownMenu

@Composable
fun FavoritePokemonScreen(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    logoutClick: () -> Unit,
    ) {
    LaunchedEffect(key1 = Unit, block = { viewModel.getAllPokemonFavorite() })
    val favoriteList = viewModel.favoriteAllFlow.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
           NavTopBar(
               title = "Favoritos",
               canNavigateBack = false ,
               navigateUp = {  },
               logoutClick = {logoutClick()},

           )
        },
        bottomBar = {
            BottomNavigationBar(
                bottomNavController = navController,
                onEvent = viewModel::onEvent,
                selected = 1
            )
        }
    ) { innerPadding ->
        FavoritePokemonContent(Modifier.padding(innerPadding), favoriteList)
    }
}


@Composable
fun FavoritePokemonContent(
    modifier: Modifier = Modifier,
    favoritesList: List<PokemonTable>,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(favoritesList) { favorite ->
            Card(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PokemonDetailTitle(name = favorite.pokemonName, fontsize = 16)

                }
                DetailPokemonImage(favorite.image)
            }
        }
    }

}






