package com.example.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonDetailTitle(name: String, fontsize: Int) {
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