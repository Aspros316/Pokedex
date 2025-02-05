package com.example.pokedex.ui.navigation

sealed class Routes(val route: String) {
    data object SplashScreen : Routes(ConstantAppScreenName.SPLASH_SCREEN)
    data object OnboardingScreen : Routes(ConstantAppScreenName.ONBOARDING_SCREEN)
    data object SignUpScreen : Routes(ConstantAppScreenName.SIGN_UP_SCREEN)
  //  data object ListScreen : Routes(ConstantAppScreenName.LIST_SCREEN)
    data object DetailsScreen : Routes(ConstantAppScreenName.DETAIL_SCREEN)
//    data object FavoritesScreen : Routes(ConstantAppScreenName.FAVORITE_SCREEN)
    data object HomeScreen : Routes(ConstantAppScreenName.HOME_SCREEN)


    //BottomNav
    data object Pokedex: Routes(ConstantAppScreenName.LIST_SCREEN)
    data object Favorites: Routes(ConstantAppScreenName.FAVORITE_SCREEN)

}

sealed interface PokemonBottomUiEvent {
    data class Paginate(val category: String) : PokemonBottomUiEvent
    object Navigate : PokemonBottomUiEvent
}



object ConstantAppScreenName {
    const val SPLASH_SCREEN = "splash_screen"
    const val ONBOARDING_SCREEN = "onboarding_screen"
    const val SIGN_UP_SCREEN = "sign_up_screen"
    const val LIST_SCREEN = "list_screen"
    const val DETAIL_SCREEN = "detail_screen"
    const val FAVORITE_SCREEN = "favorite_screen"
    const val HOME_SCREEN = "home_screen"

}