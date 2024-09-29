package com.example.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.pokedex.ui.navigation.AppScreen
import com.example.pokedex.ui.navigation.PokemonUiEvent

@Composable
fun BottomNavigationBar(
    selected: Int,
    bottomNavController: NavHostController, onEvent: (PokemonUiEvent) -> Unit
) {

    val items = listOf(
        BottomItem(
            title = "Pokedex",
            icon = Icons.Rounded.Home
        ), BottomItem(
            title = "Favoritos",
            icon = Icons.Rounded.FavoriteBorder
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(selected)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(selected = selected.intValue == index, onClick = {
                    selected.intValue = index
                    when (selected.intValue) {
                        0 -> {
                            onEvent(PokemonUiEvent.Navigate)
                            bottomNavController.popBackStack()
                            bottomNavController.navigate(AppScreen.ListScreen.route)
                        }

                        1 -> {
                            onEvent(PokemonUiEvent.Navigate)
                            bottomNavController.popBackStack()
                            bottomNavController.navigate(AppScreen.FavoritesScreen.route)
                        }
                    }
                }, icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = bottomItem.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }, label = {
                    Text(
                        text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                    )
                })
            }
        }
    }

}

data class BottomItem(
    val title: String, val icon: ImageVector
)