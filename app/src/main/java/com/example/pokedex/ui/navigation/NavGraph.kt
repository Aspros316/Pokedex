package com.example.pokedex.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.detail.DetailPokemonScreen
import com.example.pokedex.ui.favorite.FavoritePokemonScreen
import com.example.pokedex.ui.list.ListPokemonScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: PokedexViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.ListScreen.route,
    ) {

        composable(route = AppScreen.ListScreen.route) {
            ListPokemonScreen(
                navController = navController,
                viewModel = viewModel

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
            )
        }

        composable(route = ConstantAppScreenName.FAVORITE_SCREEN) {
            FavoritePokemonScreen(
                viewModel = viewModel,
                navController = navController

            )
        }
    }
}

