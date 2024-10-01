package com.example.pokedex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailPokemonImage(image: String) {
    Image(
        contentScale = ContentScale.Fit,
        painter = rememberAsyncImagePainter(image),
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp),
        contentDescription = "Background Image",
    )
}