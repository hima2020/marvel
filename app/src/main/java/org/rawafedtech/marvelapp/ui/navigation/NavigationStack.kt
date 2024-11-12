package org.rawafedtech.marvelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.presentation.details.view.CharacterDetailScreen
import org.rawafedtech.marvelapp.presentation.home.view.HomeScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreens.Home.screenRoute) {
        composable(route = NavigationScreens.Home.screenRoute) {
            HomeScreen(navController = navController)
        }
        composable(
            route = NavigationScreens.Details.screenRoute + "?character={character}"
        ) { navBackStackEntry ->
            val gson: Gson = GsonBuilder().create()
            val userJson = navBackStackEntry.arguments?.getString("character")
            val character = gson.fromJson(userJson, CharacterItem::class.java)
            CharacterDetailScreen(navController = navController, selectedCharacterItem = character)
        }

    }
}