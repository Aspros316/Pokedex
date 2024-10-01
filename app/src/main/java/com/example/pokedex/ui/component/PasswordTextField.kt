package com.example.pokedex.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.pokedex.R

@Composable
fun PasswordTextField(
    value: String,
    hint: String,
    isError: Boolean,
    errorText: String,
    modifier: Modifier,
    onTextChanged: (String) -> Unit
) {
    val defaultTextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = colorResource(id = R.color.dark_grey),
        unfocusedTextColor =colorResource(id = R.color.dark_grey),
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
        errorPlaceholderColor = colorResource(id = R.color.dark_grey),
    )

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf(false) }

    if (value.isNotEmpty()) {
        error = isError
    }
    OutlinedTextField(value = value,
        modifier = modifier,
        onValueChange = {
            onTextChanged(it)
        },
        label = { Text(hint, style = MaterialTheme.typography.titleLarge) },
        colors = defaultTextFieldColors,
        singleLine = true,
        supportingText = {
            if (error) {
                Text(text = errorText)
            }
        },
        isError = error,
        placeholder = { Text(hint, style = MaterialTheme.typography.titleLarge) },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        })
}