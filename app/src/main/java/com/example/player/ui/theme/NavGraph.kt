package com.example.player.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.player.ArtistInfoScreen
import com.example.player.LyricsScreen
import com.example.player.PlayerLayout

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "player") {
        composable("player") { PlayerLayout(navController) }  // Main Player Screen
        composable("lyrics/{songName}") { backStackEntry ->
            backStackEntry.arguments?.getString("songName")?.let {
                LyricsScreen(
                    songName = it,
                    navController = navController
                )
            }
        }
        composable("artist/{artistName}") { backStackEntry ->
            ArtistInfoScreen(
                artistName = backStackEntry.arguments?.getString("artistName"),
                navController = navController
            )
        }
    }
}


