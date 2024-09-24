package com.example.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.presentation.PokedexViewModel
import com.example.pokedex.ui.list.ListPokemonScreen

@Composable
fun NavGraph(navController: NavHostController) {

    val pokedexViewModel = hiltViewModel<PokedexViewModel>()


    NavHost(
        navController = navController,
        startDestination = ConstantAppScreenName.LIST_SCREEN,
    ) {

        composable(route = ConstantAppScreenName.LIST_SCREEN) {
            ListPokemonScreen(
                pokedexViewModel = pokedexViewModel,
            )
        }

    }
}

/*
@Composable
fun NavGraph(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route,
    ) {

        composable(route = AppScreen.HomeScreen.route) {
            HomeScreen(
                mainViewModel = mainViewModel,
                navController = navController
            )
        }

        composable(route = AppScreen.DetailsScreen.route) {
            DetailsScreen(mainViewModel)
        }
    }
}*/
