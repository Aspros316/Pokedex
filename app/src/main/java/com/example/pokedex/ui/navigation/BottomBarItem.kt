package com.example.pokedex.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Pokedex(
        override val route: String = Routes.Pokedex.route,
        override val title: String = "Pokedex",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "")
        }
    ) : BottomBarItem()

    data class Favorites(
        override val route: String = Routes.Favorites.route,
        override val title: String = "Favorites",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = "")
        }
    ) : BottomBarItem()

}