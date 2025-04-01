package com.example.popcornchallenge.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.popcornchallenge.core.network.ktor.KtorNetworkClient
import com.example.popcornchallenge.data.remote.IMDBRemoteRemoteDataSource
import com.example.popcornchallenge.domain.repository.MovieDetailsRepository
import com.example.popcornchallenge.domain.usecase.GetMovieDetailUseCase
import com.example.popcornchallenge.presentation.screens.DiscoverMovieScreen
import com.example.popcornchallenge.presentation.screens.ItemDetailScreen
import com.example.popcornchallenge.presentation.viewmodel.DiscoverEvent
import com.example.popcornchallenge.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun PopCornApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    MainNavHost(navController = navController, innerPadding = innerPadding)
}

@Composable
fun MainNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val viewModel = MovieDetailsViewModel(
        GetMovieDetailUseCase(
            MovieDetailsRepository(
                IMDBRemoteRemoteDataSource(
                    KtorNetworkClient.client
                )
            )
        )
    )
    NavHost(navController = navController, startDestination = MovieDiscover) {
        composable<MovieDiscover> {
            DiscoverMovieScreen(innerPadding) { onEvent ->
                when (onEvent) {
                    DiscoverEvent.GetMovies -> Unit
                    is DiscoverEvent.NavigateDetail -> navController.navigate(
                        MovieDetail(
                            onEvent.id
                        )
                    )
                }
            }
        }

        composable<MovieDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<MovieDetail>()
            ItemDetailScreen(innerPadding, args.id, viewModel)
        }
    }
}
