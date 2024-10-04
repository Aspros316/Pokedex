package com.example.pokedex.ui.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.NavTopBar
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.ui.navigation.AppScreen

@Composable
fun SignUpScreen(
    viewModel: PokemonViewModel,
    navController: NavHostController,
    onNavigateToSignUp: () -> Unit,
    signInRequest: (SignUpCredentials) -> Unit,
) {

    val credentials = viewModel.getSignUpFlow.collectAsStateWithLifecycle().value

    println("credentials ${credentials}")
    if (credentials.isValid) {
        navController.navigate(AppScreen.ListScreen.route)
    } else {
        SignInScreenContent(signInRequest, onNavigateToSignUp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreenContent(
    signInRequest: (SignUpCredentials) -> Unit,
    onNavigateToSignUp: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
           TopAppBar(
               title = { Text(text = "Registro") }
           )
        },

        ) { innerPadding ->


        Surface(
            modifier = Modifier.padding(innerPadding)
        )
        {
            SignIn(onNavigateToSignUp, signInRequest)
        }
    }
}
