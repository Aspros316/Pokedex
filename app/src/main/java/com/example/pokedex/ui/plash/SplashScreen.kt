package com.example.pokedex.ui.plash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pokedex.R
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.navigation.AppScreen
import com.example.pokedex.ui.signUp.SignInScreenContent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: PokemonViewModel,
    navController: NavHostController,
    navigateToSignUp: () -> Unit,
    ){
    LaunchedEffect(key1 = Unit, block = { viewModel.getIsViewedOnboarding() })

    val isViewedOnboarding = viewModel.isViewedOnboarding.collectAsStateWithLifecycle()

    val scale = remember { Animatable(0f) }
    val context = LocalContext.current
    val name = remember { mutableStateOf("") }


    LaunchedEffect(key1 = true, block = {

        scale.animateTo(
            targetValue = 1f,
            tween(
                durationMillis = 1600,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(2000L)
        if (isViewedOnboarding.value) {
            navController.navigate(AppScreen.SignUpScreen.route)
        } else {
            navController.navigate(AppScreen.OnboardingScreen.route)
        }
    })

    Surface(
        modifier = Modifier
            .background(colorResource(id = R.color.black))
            .padding(16.dp)
            .fillMaxSize()
            .scale(scale.value),
        shape = RectangleShape,
    )
    {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.black))
                .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription =  null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(252.dp)
                    .width(300.dp)

            )
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

        }
    }
}