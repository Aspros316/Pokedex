package com.example.pokedex.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun NormalText(text: String, modifier: Modifier, color: Color, font: Int) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = font.sp,
        color = color
    )
}