package com.example.pokedex.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.detail.DetailPokemonScreen
import com.example.pokedex.ui.favorite.FavoritePokemonScreen
import com.example.pokedex.ui.home.HomePokemonScreen
import com.example.pokedex.ui.list.ListPokemonScreen
import com.example.pokedex.ui.model.SignUpCredentials
import com.example.pokedex.ui.signUp.SignInScreenContent
import com.example.pokedex.ui.signUp.SignUpScreen
import java.lang.Thread.sleep

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: PokemonViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.SignUpScreen.route,
    ) {

        composable(route = AppScreen.SignUpScreen.route) {
            SignUpScreen(
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
                navController = navController,
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
            val idPokemon = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailPokemonScreen(
                viewModel = viewModel,
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
                viewModel = viewModel,
                navController = navController,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(AppScreen.SignUpScreen.route)
                }
            )
        }
    }
}

