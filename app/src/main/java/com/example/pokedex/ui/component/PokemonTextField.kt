package com.example.pokedex.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.pokedex.R

@Composable
fun PokemonTextField(
    value: String,
    hint: String,
    isError: Boolean,
    errorText: String,
    modifier: Modifier,
    onTextChanged: (String) -> Unit
){
    val defaultTextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = colorResource(id = R.color.dark_grey),
        unfocusedTextColor = colorResource(id = R.color.dark_grey),
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        focusedPlaceholderColor = colorResource(id = R.color.dark_grey),
        unfocusedPlaceholderColor = colorResource(id = R.color.dark_grey),
        focusedLabelColor = colorResource(id = R.color.orange),
        unfocusedLabelColor = colorResource(id = R.color.dark_grey),
        unfocusedIndicatorColor = colorResource(id = R.color.dark_grey),
        focusedIndicatorColor = colorResource(id = R.color.orange),
        errorContainerColor = Color.Transparent,
        errorTextColor = colorResource(id = R.color.dark_grey),
        errorPlaceholderColor = colorResource(id = R.color.dark_grey)
    )

    var error by rememberSaveable { mutableStateOf(false) }
    if (value.isNotEmpty()) {
        error = isError
    } else if (value.isEmpty() &&
        errorText == "Este correo ya existe en nuestra base de datos"
    ) {
        error = isError
    }
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            onTextChanged(it)
        },
        supportingText = {
            if (error) {
                Text(text = errorText)
            }
        },
        isError = error,
        singleLine = true,
        label = { Text(hint, style = MaterialTheme.typography.titleLarge) },
        placeholder = { Text(hint, style = MaterialTheme.typography.titleLarge) },
        colors = defaultTextFieldColors,

        )
}