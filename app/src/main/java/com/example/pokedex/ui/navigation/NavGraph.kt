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
import com.example.pokedex.ui.detail.DetailPokemonScreen
import com.example.pokedex.ui.favorite.FavoritePokemonScreen
import com.example.pokedex.ui.list.ListPokemonScreen
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
        startDestination = AppScreen.SplashScreen.route,
    ) {
        composable(route = AppScreen.SignUpScreen.route) {
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
                    navController.navigate(AppScreen.ListScreen.route)
                }

            )
        }

        composable(route = AppScreen.ListScreen.route) {
            ListPokemonScreen(
                analytics = analytics,
                navController = navController,
                navToDetail = { pokemon ->
                    navController.navigate(AppScreen.DetailsScreen.route + "/${pokemon.getUrlNumber()}" + "/?${pokemon.name}")
                },
                viewModel = viewModel,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(AppScreen.SignUpScreen.route)
                }

            )
        }

        composable(route = AppScreen.DetailsScreen.route + "/{id}" + "/?{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
            ),

            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
            val idPokemon = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailPokemonScreen(
                analytics = analytics,
                viewModel = pokemonDetailViewModel,
                navigateUp = { navController.navigateUp() },
                idPokemon = idPokemon,
                name = name,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(AppScreen.SignUpScreen.route)
                }
            )
        }

        composable(route = ConstantAppScreenName.FAVORITE_SCREEN) {
            FavoritePokemonScreen(
                analytics = analytics,
                viewModel = viewModel,
                navController = navController,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(AppScreen.SignUpScreen.route)
                }
            )
        }

        composable(route = AppScreen.SplashScreen.route) {
            SplashScreen(
                viewModel = viewModel,
                navController = navController,
                navigateToSignUp = {
                    navController.navigate(AppScreen.OnboardingScreen.route)
                }
            )
        }

        composable(route = AppScreen.OnboardingScreen.route) {
            OnboardingScreen(
               navigateNextPage = {
                   navController.navigate(AppScreen.SignUpScreen.route)
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

