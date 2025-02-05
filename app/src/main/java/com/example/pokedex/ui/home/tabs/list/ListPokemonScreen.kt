package com.example.pokedex.ui.home.tabs.list

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.PokemonListViewModel
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.ErrorState
import com.example.pokedex.ui.component.Loader
import com.example.pokedex.ui.component.PagingLoadingState
import com.example.pokedex.ui.component.PagingType
import com.example.pokedex.ui.component.PagingWrapper
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
    viewModel: PokemonListViewModel,
    logoutClick: () -> Unit,
) {
    TrackScreen(name = "ingreso a LisPokemonScreen", analytics = analytics)

    val pokemonPage: LazyPagingItems<Pokemon> =
        viewModel.pokemonList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getListPokemon()
    })

    //Collecting states from ViewModel
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
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
                    pokemonPage = pokemonPage,
                    analytics = analytics,
                    navToDetail = navToDetail
                )
            }
        },
    ) {}
}

@Composable
private fun ListContent(
    pokemonPage: LazyPagingItems<Pokemon>,
    analytics: FirebaseAnalytics,
    navToDetail: (Pokemon) -> Unit,
) {
    PagingWrapper(
        pagingType = PagingType.LAZY_ROW,
        pagingItems = pokemonPage,
        initialView = { PagingLoadingState() },
        itemView = {
            PokemonItemList(
                pokemon = it,
                analytics = analytics
            ) { characterModel -> navToDetail(characterModel) }
        },

        )
}

@Composable
fun PokemonItemList(
    pokemon: Pokemon,
    analytics: FirebaseAnalytics,
    onItemSelected: (Pokemon) -> Unit,
) {

    PokemonText(pokemon)
    PokemonCard(analytics, onItemSelected, pokemon)
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

    SubcomposeAsyncImage(
        modifier = Modifier
            .background(Color.White)
            .height(200.dp)
            .fillMaxWidth(),
        model = image,
        contentDescription = null,
        // contentScale = ContentScale.Crop,
        loading = { PagingLoadingState() },

        )
}

