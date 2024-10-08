package com.example.pokedex.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.data.cache.model.PokemonTable
import com.example.pokedex.domain.model.Abilities
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Moves
import com.example.pokedex.domain.model.Types
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.DetailPokemonImage
import com.example.pokedex.ui.component.Loader
import com.example.pokedex.ui.component.NavTopBar
import com.example.pokedex.ui.component.PokemonDetailTitle
import com.example.pokedex.utils.sealed.Result
import com.example.pokedex.utils.sealed.Result.OnSuccess

@Composable
fun DetailPokemonScreen(
    viewModel: PokemonViewModel,
    navigateUp: () -> Unit,
    idPokemon: Int,
    name: String,
    logoutClick: () -> Unit,
) {
    LaunchedEffect(key1 = Unit, block = { viewModel.getPokemonDetail(name) })

    LaunchedEffect(key1 = idPokemon, block = { viewModel.getPokemonFavorite(idPokemon) })

    val detailUiState = viewModel.detailStateFlow.collectAsStateWithLifecycle()

    val isFavorite =
        viewModel.getFavoriteFlow.collectAsStateWithLifecycle().value?.isFavorite ?: false

    DetailPokemonState(
        detailUiState.value,
        navigateUp,
        idPokemon,
        name,
        viewModel,
        isFavorite,
        logoutClick
    )
}


@Composable
fun DetailPokemonState(
    value: Result<DetailPokemon>,
    navigateUp: () -> Unit,
    idPokemon: Int,
    name: String,
    viewModel: PokemonViewModel,
    isFavorite: Boolean,
    logoutClick: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Pokemon",
                canNavigateBack = true,
                navigateUp = navigateUp,
                logoutClick = { logoutClick() }

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

            is OnSuccess -> {
                DetailPokemonContent(
                    value.data,
                    modifier = Modifier.padding(innerPadding),
                    name = name,
                    idPokemon = idPokemon,
                    viewModel,
                    isFavorite
                )
            }

            is Result.OnError -> {}
            is Result.OnEmpty -> {}
        }
    }
}

@Composable
fun DetailPokemonContent(
    detailPokemon: DetailPokemon,
    modifier: Modifier,
    name: String,
    idPokemon: Int,
    viewModel: PokemonViewModel,
    isFavorite: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        println("detallepokemon ${detailPokemon}")

        ElevatedCard(
            modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            var selected by remember { mutableStateOf(isFavorite) }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PokemonDetailTitle(name = name, fontsize = 16)
                ToggleHeart(
                    checked = selected,
                    onCheckedChange = { favoriteSelected ->
                        if (favoriteSelected) {
                            viewModel.savePokemonFavorite(
                                PokemonTable(
                                    idPokemon,
                                    name,
                                    true,
                                    detailPokemon.sprite.frontDefault.toString()
                                )
                            )
                        } else {
                            viewModel.deletePokemonFavorite(idPokemon)
                        }
                        selected = favoriteSelected
                    }
                )
            }
            DetailPokemonImage(detailPokemon.sprite.frontDefault)
        }
        DisplayListComponent(detailPokemon = detailPokemon)
    }
}


@Composable
fun DisplayListComponent(
    detailPokemon: DetailPokemon,
) {


    TabScreen(detailPokemon)
    /*  Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
    ) {

        }*/

    /*       Text(
                   text = "Habilidades",
                   fontWeight = FontWeight.Black,
                   color = Color.Black,
                   style = typography.bodyMedium,
                   textAlign = TextAlign.Center,
                   modifier = Modifier
                       .align(alignment = Alignment.CenterHorizontally),
               )

               Spacer(modifier = Modifier.size(8.dp))

               LazyColumn(
                   modifier = Modifier
                       .fillMaxWidth()
                       .background(Color.White),

                   ) {
                   items(abilities) { abilitie ->
                       Card(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(top = 8.dp),
                       ) {
                           Text(
                               modifier = Modifier.padding(16.dp),
                               text = abilitie.ability.name,
                               fontWeight = FontWeight.Black,
                               color = Color.Black,
                           )
                       }
                   }
               }*/


}


@Composable
fun ToggleHeart(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    IconToggleButton(checked = checked, onCheckedChange = onCheckedChange) {
        Icon(
            modifier = Modifier
                .size(28.dp),
            imageVector = if (checked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = Color.Red
        )
    }
}

@Composable
fun TabScreen(
    detailPokemon: DetailPokemon
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Habilidades", "Tipos de habilidades", "Movimientos")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title, fontSize = 16.sp) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ShowAbilities(detailPokemon.abilities)
            1 -> ShowTypes(detailPokemon.types)
            2 -> ShoMoves(detailPokemon.moves)
        }
    }
}

@Composable
fun ShowAbilities(
    listAbilities: List<Abilities>
) {
    ShowList(
        items = listAbilities
    ) { abilitie ->
        Text(
            modifier = Modifier.padding(16.dp),
            text = abilitie.ability.name,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )
    }
}

@Composable
fun ShowTypes(
    listTypes: List<Types>
) {
    ShowList(
        items = listTypes
    ) { types ->
        Text(
            modifier = Modifier.padding(16.dp),
            text = types.type.name,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )
    }
}


@Composable
fun ShoMoves(
    listMoves: List<Moves>
) {
    ShowList(
        items = listMoves
    ) { moves ->
        Text(
            modifier = Modifier.padding(16.dp),
            text = moves.move.name,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )
    }
}

@Composable
fun <T> ShowList(
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    Spacer(modifier = Modifier.size(8.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            ) {
                itemContent(item)
            }
        }
    }
}


