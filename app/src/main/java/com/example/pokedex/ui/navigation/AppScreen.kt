package com.example.pokedex.ui.navigation

sealed class AppScreen(val route: String) {
    object ListScreen : AppScreen(ConstantAppScreenName.LIST_SCREEN)
    object DetailsScreen : AppScreen(ConstantAppScreenName.DETAIL_SCREEN)
    object FavoritesScreen : AppScreen(ConstantAppScreenName.FAVORITE_SCREEN)
}

sealed interface PokemonUiEvent {
    data class Paginate(val category: String) : PokemonUiEvent
    object Navigate : PokemonUiEvent
}


object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val LIST_SCREEN = "list_screen"
    const val DETAIL_SCREEN = "detail_screen"
    const val FAVORITE_SCREEN = "favorite_screen"
}