package com.example.pokedex.ui.navigation

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    object DetailsScreen : AppScreen(ConstantAppScreenName.DETAILS_SCREEN)
}


object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val LIST_SCREEN = "list_screen"
    const val DETAILS_SCREEN = "details_screen"
}