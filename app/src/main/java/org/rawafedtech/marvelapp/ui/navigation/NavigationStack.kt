package org.rawafedtech.marvelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.rawafedtech.marvelapp.presentation.home.view.HomeScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreens.Home.screenRoute) {
        composable(route = NavigationScreens.Home.screenRoute) {
            HomeScreen(navController = navController)
        }

    }
}