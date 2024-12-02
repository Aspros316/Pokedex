package com.example.pokedex.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.presentation.PokemonDetailViewModel
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.home.tabs.detail.DetailPokemonScreen
import com.example.pokedex.ui.home.HomePokemonScreen
import com.example.pokedex.ui.home.tabs.favorite.FavoritePokemonScreen
import com.example.pokedex.ui.home.tabs.list.ListPokemonScreen
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.ui.onboarding.OnboardingScreen
import com.example.pokedex.ui.plash.SplashScreen
import com.example.pokedex.ui.signUp.SignUpScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import java.lang.Thread.sleep

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: PokemonViewModel,
    analytics: FirebaseAnalytics
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route,
    ) {
        composable(route = Routes.SignUpScreen.route) {
            SignUpScreen(
                analytics = analytics,
                viewModel = viewModel,
                navController = navController,
                onNavigateToSignUp = {},
                signInRequest = {
                    viewModel.saveSignUp(
                        SignUpCredentials(
                            it.username, it.password, it.isValid
                        )
                    )
                    navController.navigate(Routes.HomeScreen.route)
                }

            )
        }

        composable(route = Routes.HomeScreen.route) {
            HomePokemonScreen(
            )
        }

       /* composable(route = Routes.ListScreen.route) {
            ListPokemonScreen(
                analytics = analytics,
                navController = navController,
                navToDetail = { pokemon ->
                    navController.navigate(Routes.DetailsScreen.route + "/${pokemon.getUrlNumber()}" + "/?${pokemon.name}")
                },
                viewModel = viewModel,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(Routes.SignUpScreen.route)
                }

            )
        }*/



       /* composable(route = ConstantAppScreenName.FAVORITE_SCREEN) {
            FavoritePokemonScreen(
                analytics = analytics,
                viewModel = viewModel,
                navController = navController,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(Routes.SignUpScreen.route)
                }
            )
        }*/

        composable(route = Routes.SplashScreen.route) {
            SplashScreen(
                viewModel = viewModel,
                navController = navController,
                navigateToSignUp = {
                    navController.navigate(Routes.OnboardingScreen.route)
                }
            )
        }

        composable(route = Routes.OnboardingScreen.route) {
            OnboardingScreen(
               navigateNextPage = {
                   navController.navigate(Routes.SignUpScreen.route)
               },
                saveOnboarding = {
                    viewModel.storeViewedOnboarding()
                }
            )
        }

    }
}


@Composable
fun TrackScreen(name: String, analytics: FirebaseAnalytics) {
    DisposableEffect(key1 = Unit) {
        onDispose {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            }
        }
    }
}

