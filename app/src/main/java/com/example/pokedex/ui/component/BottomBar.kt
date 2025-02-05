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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokedex.ui.navigation.BottomBarItem
import com.example.pokedex.ui.navigation.PokemonBottomUiEvent
import com.example.pokedex.ui.navigation.Routes

@Composable
fun BottomNavigationBar(
    items: List<BottomBarItem>,
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    /* val selected = rememberSaveable {
         mutableIntStateOf(selected)
     }*/

    NavigationBar {
     /*   Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {*/


            items.forEach { item ->
                NavigationBarItem(
                    icon = item.icon,
                    onClick = {},
                    selected = currentDestination?.hierarchy?.any{ it.route == item.route } == true
                )
            }
    }
}
                    /*label = {
                        Text(
                            text = item.title, color = MaterialTheme.colorScheme.onBackground
                        )
                    }*/

