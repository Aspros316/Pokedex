package com.example.pokedex.ui.list

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.BottomNavigationBar
import com.example.pokedex.ui.component.ErrorState
import com.example.pokedex.ui.component.Loader
import com.example.pokedex.ui.navigation.TrackScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPokemonScreen(
    analytics: FirebaseAnalytics,
    navController: NavHostController,
    navToDetail: (Pokemon) -> Unit,
    viewModel: PokemonViewModel,
    logoutClick: () -> Unit,
) {
    TrackScreen(name = "ingreso a LisPokemonScreen", analytics = analytics)


    //Collecting states from ViewModel
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            SearchBar(
                query = searchText,//text showed on SearchBar
                onQueryChange = viewModel::onSearchTextChange, //update the value of searchText
                onSearch = viewModel::onSearchTextChange, //the callback to be invoked when the input service triggers the ImeAction.Search action
                active = isSearching, //whether the user is searching or not
                onActiveChange = { viewModel.onToogleSearch() }, //the callback to be invoked when this search bar's active state is changed
                placeholder = { Text(text = "Busca tu pokemon !") },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        enabled = searchText.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                ListContent(
                    analytics = analytics,
                    navToDetail = navToDetail,
                    viewModel = viewModel,
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                bottomNavController = navController,
                onEvent = viewModel::onEvent,
                selected = 0
            )
        }

    ) {

        /*    innerPadding ->
        ListContent(
            analytics = analytics,
            navToDetail = navToDetail,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )*/
    }
}

@Composable
private fun ListContent(
    analytics: FirebaseAnalytics,
    navToDetail: (Pokemon) -> Unit,
    viewModel: PokemonViewModel,
) {
    val pokemonPage: LazyPagingItems<Pokemon> =
        viewModel.pokemonList.collectAsLazyPagingItems()


    LaunchedEffect(key1 = Unit, block = {
        viewModel.getListPokemon()
    })

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFF2B2626))
    ) {
        items(pokemonPage.itemCount) { index ->
            val pokemon = pokemonPage[index]
            if (pokemon != null) {
                PokemonText(pokemon)
                PokemonCard(analytics, navToDetail, pokemon)
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
                        Column(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Loader()
                        }
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


@Composable
fun PokemonText(pokemon: Pokemon) {
    Text(
        text = pokemon.name,
        fontWeight = FontWeight.Black,
        color = Color.White,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun PokemonCard(
    analytics: FirebaseAnalytics,
    navToDetail: (Pokemon) -> Unit,
    pokemon: Pokemon
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(8.dp),
        onClick = {
            navToDetail(pokemon)
            analytics.logEvent("Go_to_detail") {
                param("id", "${pokemon.id}")
                param("name", pokemon.name)
            }
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier,
        ) {
            PokemonImage(pokemon.artwork)
        }
    }
}


@Composable
fun PokemonImage(image: String) {
    Image(
        painter = rememberAsyncImagePainter(image),
        contentDescription = null,
        modifier = Modifier
            .background(Color.White)
            .height(200.dp)
            .fillMaxWidth()
    )
}

