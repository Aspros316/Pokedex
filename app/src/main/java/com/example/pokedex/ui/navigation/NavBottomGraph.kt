package com.example.pokedex.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedex.presentation.PokemonDetailViewModel
import com.example.pokedex.presentation.PokemonListViewModel
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.home.tabs.detail.DetailPokemonScreen
import com.example.pokedex.ui.home.tabs.favorite.FavoritePokemonScreen
import com.example.pokedex.ui.home.tabs.list.ListPokemonScreen
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import java.lang.Thread.sleep

@Composable
fun NavBottomGraph(navController: NavHostController) {
    val viewModel = hiltViewModel<PokemonViewModel>()
    val analytics = Firebase.analytics
    NavHost(navController = navController, startDestination = Routes.Pokedex.route) {

        composable(route = Routes.Pokedex.route){
            val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
            ListPokemonScreen(
                analytics = analytics,
                navController = navController,
                navToDetail = { pokemon ->
                    navController.navigate(Routes.DetailsScreen.route + "/${pokemon.getUrlNumber()}" + "/?${pokemon.name}")
                },
                viewModel = pokemonListViewModel,
                logoutClick = {
                    viewModel.clearDatastore()
                    sleep(1000)
                    navController.navigate(Routes.SignUpScreen.route)
                }

            )
        }

        composable(route = Routes.DetailsScreen.route + "/{id}" + "/?{name}",
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
                    navController.navigate(Routes.SignUpScreen.route)
                }
            )
        }

        composable(route = Routes.Favorites.route){
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
        }
    }
}
