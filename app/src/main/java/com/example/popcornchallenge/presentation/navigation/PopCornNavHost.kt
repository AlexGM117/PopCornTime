package com.example.popcornchallenge.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.presentation.screens.DiscoverMovieScreen
import com.example.popcornchallenge.presentation.screens.ItemDetailScreen
import com.example.popcornchallenge.presentation.viewmodel.DiscoverEvent
import com.example.popcornchallenge.presentation.viewmodel.MovieDetailsViewModel
import com.example.popcornchallenge.presentation.viewmodel.MovieDiscoverViewModel

@Composable
fun PopCornApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    MainNavHost(navController = navController, innerPadding = innerPadding)
}

@Composable
fun MainNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = MovieDiscover) {
        composable<MovieDiscover> {
            val viewModelMovieDiscover = hiltViewModel<MovieDiscoverViewModel>()
            val moviePagingItems: LazyPagingItems<Movie> = viewModelMovieDiscover.movieState.collectAsLazyPagingItems()

            DiscoverMovieScreen(innerPadding, moviePagingItems) { onEvent ->
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
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            val args = backStackEntry.toRoute<MovieDetail>()
            ItemDetailScreen(innerPadding, args.id, viewModel)
        }
    }
}
