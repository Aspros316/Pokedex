package com.example.pokedex.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.component.BottomNavigationBar
import com.example.pokedex.ui.navigation.BottomBarItem
import com.example.pokedex.ui.navigation.BottomBarItem.Favorites
import com.example.pokedex.ui.navigation.BottomBarItem.Pokedex
import com.example.pokedex.ui.navigation.NavBottomGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePokemonScreen() {

    val items = listOf(Pokedex(), Favorites())
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBarNavigation(
            items = items,
            navController = navController
        )
    }) {
        Box {
            NavBottomGraph(
                navController = navController
            )
        }
    }
}


@Composable
fun BottomBarNavigation(items: List<BottomBarItem>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        /*   Row(
               modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
           ) {*/


        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = {Text(text = item.title)},
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                inclusive = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }

}



