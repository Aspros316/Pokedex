package com.example.pokedex.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.data.cache.model.PokemonDb
import com.example.pokedex.domain.model.Abilitie
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

    DetailPokemonState(uiState.value, navigateUp,idPokemon, name, viewModel)
}

@Composable
fun DetailPokemonState(
    value: Result<DetailPokemon>,
    navigateUp: () -> Unit,
    idPokemon: Int,
    name: String,
    viewModel: PokedexViewModel,
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
                    value.data,
                    modifier = Modifier.padding(innerPadding),
                    name = name,
                    idPokemon = idPokemon,
                    viewModel
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
    viewModel: PokedexViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            var selected by remember { mutableStateOf(false) }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PokemonTitle(name = name, fontsize = 16)

                ToggleHeart(
                    checked = selected,
                    onCheckedChange = { favoriteSelected ->
                        if (favoriteSelected){
                            viewModel.savePokemonFavorite(PokemonDb(idPokemon, name, false))
                            Log.i("selected", "${favoriteSelected}")
                        }else {
                            Log.i("no selected", "${favoriteSelected}")
                        }
                        selected = favoriteSelected
                    }
                )
            }



            DetailPokemonImage(detailPokemon)
        }
        DisplayListComponent(abilities = detailPokemon.remoteAbilities)
    }
}

@Composable
fun DetailPokemonImage(detailPokemon: DetailPokemon) {
    Image(
        contentScale = ContentScale.Fit,
        painter = rememberAsyncImagePainter(detailPokemon.remoteSprites.frontDefault),
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp),
        contentDescription = "Background Image",
    )
}


@Composable
fun DisplayListComponent(
    abilities: List<Abilitie>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
    ) {


        Text(
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
        }
    }
}


@Composable
fun PokemonTitle(name: String, fontsize: Int) {
    Text(
        text = name,
        fontSize = fontsize.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 8.dp, end = 8.dp)
    )
}

@Composable
fun ToggleHeart(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    IconToggleButton(checked = checked, onCheckedChange = onCheckedChange) {
        Icon(
            modifier = Modifier
                .size(28.dp),
            imageVector = if(checked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = Color.Red
        )
    }
}