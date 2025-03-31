package com.example.popcornchallenge.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.popcornchallenge.presentation.screens.DiscoverMovieScreen
import com.example.popcornchallenge.presentation.screens.ItemDetailScreen

@Composable
fun PopCornApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    MainNavHost(navController = navController, innerPadding = innerPadding)
}

@Composable
fun MainNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = First) {
        composable<First> {
            DiscoverMovieScreen(innerPadding) {
                navController.navigate(Second)
            }
        }

        composable<Second> {
            ItemDetailScreen(innerPadding)
        }
    }
}
