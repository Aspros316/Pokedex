package com.example.pokedex.ui.list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.R
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.component.Loader
import com.example.pokedex.ui.composables.NavTopBar
import com.example.pokedex.ui.composables.PokemonScreenTopBar
import com.example.pokedex.ui.navigation.ConstantAppScreenName

@Composable
fun ListPokemonScreen(
    navController: NavController,
    pokedexViewModel: PokedexViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Pokemon",
                canNavigateBack = false,
                navigateUp = {}
                )
        },


    ) { innerPadding ->
        ListContent(
            navController = navController,
            viewModel = pokedexViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ListContent(
    navController: NavController,
    viewModel: PokedexViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val pokemonPage: LazyPagingItems<Pokemon> =
            viewModel.pokemonListState.collectAsLazyPagingItems()

        LaunchedEffect(key1 = Unit, block = {
            viewModel.getListPokemon()
        })

        LazyColumn(
            modifier = Modifier
                .background(Color(0xFF2B2626))
        ) {
            items(pokemonPage.itemCount) { index ->
                val item = pokemonPage[index]
                if (item != null) {
                    PokemonCard(navController, item)
                    PokemonText(item)
                }
            }

            pokemonPage.apply {
                when {
                    // FIRST LOAD
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Loader()
                            }
                        }
                    }

                    // GOT ERROR ON FIRST LOAD
                    loadState.refresh is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Loader()
                            }
                        }
                    }

                    // LOADING A NEXT PAGE
                    loadState.append is LoadState.Loading -> {
                        item {
                            Loader()
                        }
                    }

                    // GOT AN ERROR AFTER LOADING SOME SUBSEQUENT PAGE
                    loadState.append is LoadState.Error -> {
                        item {
                            ErrorState()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PokemonText(pokemon: Pokemon) {
    Text(
        text = pokemon.name,
        fontWeight = FontWeight.Black,
        color = Color.White,
        modifier = Modifier.padding(start = 10.dp)
    )
}


@Composable
fun PokemonCard(navController: NavController,pokemon: Pokemon) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        onClick = {
            Log.i("index", "indice ${pokemon.getUrlNumber()}")
            navController.navigate(ConstantAppScreenName.DETAIL_SCREEN+ "/${pokemon.getUrlNumber()}" + "/?${pokemon.name}")
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier,
        ) {
            MainImage(pokemon.getImageUrl())
        }
    }
}





@Composable
fun MainImage(image: String) {
    Image(
        painter = rememberAsyncImagePainter(image),
        contentDescription = null,
        modifier = Modifier
            .background(Color.White)
            .height(150.dp)
            .fillMaxWidth()
    )
}


@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.error_icon),
            contentDescription = null
        )
        Text(
            text = "You got an error!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
