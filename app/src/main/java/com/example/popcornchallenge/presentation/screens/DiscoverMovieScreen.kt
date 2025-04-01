package com.example.popcornchallenge.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.popcornchallenge.core.network.ktor.KtorNetworkClient
import com.example.popcornchallenge.data.remote.IMDBRemoteRemoteDataSource
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.repository.MovieDiscoverRepository
import com.example.popcornchallenge.domain.usecase.GetMovieDiscoverUseCase
import com.example.popcornchallenge.presentation.components.MovieCard
import com.example.popcornchallenge.presentation.viewmodel.DiscoverEvent
import com.example.popcornchallenge.presentation.viewmodel.MovieDiscoverViewModel

@Composable
fun DiscoverMovieScreen(innerPadding: PaddingValues, onEvent: (DiscoverEvent) -> Unit) {
    val viewModel = MovieDiscoverViewModel(GetMovieDiscoverUseCase(MovieDiscoverRepository(IMDBRemoteRemoteDataSource(KtorNetworkClient.client))))
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.movieState.collectAsLazyPagingItems()
    DiscoverMovieScreenContent(innerPadding, moviePagingItems, onEvent)
}

@Composable
fun DiscoverMovieScreenContent(
    innerPadding: PaddingValues,
    moviePagingItems: LazyPagingItems<Movie>,
    onEvent: (DiscoverEvent) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .padding(innerPadding)
    ) {
        items(moviePagingItems.itemCount) { index ->
            moviePagingItems[index]?.let { movie ->
                MovieCard(movie = movie, onEvent = onEvent)
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true,
    device = PIXEL_4_XL
)
@Composable
private fun DiscoverScreenPreview() {

}