package com.example.pokedex.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.pokedex.R

@Composable
fun PokemonButton(
    onClick: () -> Unit,
    txtButton: String,
    isEnable: Boolean,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = { onClick() }, enabled = isEnable, colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.orange),
            disabledContainerColor = colorResource(id = R.color.dark_grey),
            disabledContentColor = colorResource(id = R.color.light_grey),
            contentColor = colorResource(id = R.color.white)
        )
    ) {
        Text(
            text = txtButton, fontSize = 18.sp,
            color = colorResource(id = R.color.white)
        )
    }
}