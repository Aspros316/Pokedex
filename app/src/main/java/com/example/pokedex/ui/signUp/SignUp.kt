package com.example.pokedex.ui.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.ui.component.NormalText
import com.example.pokedex.ui.component.PasswordTextField
import com.example.pokedex.ui.component.PokemonButton
import com.example.pokedex.ui.component.PokemonTextField
import com.example.pokedex.ui.model.SignUpCredentials

@Composable
fun SignIn(
    onNavigateToSignUp: () -> Unit,
    signInRequest: (SignUpCredentials) -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)


    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .width(720.dp)
                .align(Alignment.Center)
        ) {

            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "",
                modifier = Modifier
                    .height(168.dp)
                    .width(200.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            PokemonTextField(
                value = email,
                hint = "Email",
                isError = false,
                errorText = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) { email = it }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            PasswordTextField(
                value = password,
                hint = "Contraseña",
                isError = false,
                errorText = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) { password = it }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                PokemonButton(
                    onClick = {
                        signInRequest(
                            SignUpCredentials(email, password, true)
                        )
                    },
                    txtButton = "Iniciar sesión",
                    isEnable = true,
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
/*                Row(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            end = 16.dp
                        )
                ) {
                    NormalText(
                        text = "¿No tienes cuenta?",
                        color = Color.Black,
                        modifier = Modifier,
                        font = 19
                    )
                    NormalText(
                        text = "Crear cuenta",
                        color = colorResource(id = R.color.light_orange),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                onNavigateToSignUp()
                            },
                        font = 19
                    )
                }*/
            }
        }
    }
}
