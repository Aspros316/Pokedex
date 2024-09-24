package com.example.pokedex.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.component.Loader

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
            .fillMaxSize()
    ) {
        val gamesPage: LazyPagingItems<Pokemon> =
            viewModel.pokemonListState.collectAsLazyPagingItems()

        //   Log.i("response","$gamesPage")

        LazyColumn(
            modifier = Modifier
                .background(Color(0xFF2B2626))
        ) {
            items(gamesPage.itemCount) { index ->
                val item = gamesPage[index]
                if (item != null) {
                    PokemonCard(item)
                    PokemonText(item)
                }
            }
            when (gamesPage.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
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

                is LoadState.Error -> {
                    item {
                        Text(text = "Error al cargar")
                    }
                }
            }
        }
    }
}


@Composable
fun PokemonText(pokemon: Pokemon) {
    Text(
        text = pokemon.name.toString(),
        fontWeight = FontWeight.Black,
        color = Color.White,
        modifier = Modifier.padding(start = 10.dp)
    )
}


@Composable
fun PokemonCard(pokemon: Pokemon) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        onClick = {},
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier,
        ) {
            MainImage(pokemon.getImageUrl())
        }
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
