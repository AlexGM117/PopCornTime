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
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.presentation.components.MovieCard
import com.example.popcornchallenge.presentation.viewmodel.DiscoverEvent

@Composable
fun DiscoverMovieScreen(
    innerPadding: PaddingValues,
    moviePagingItems: LazyPagingItems<Movie>,
    onEvent: (DiscoverEvent) -> Unit
) {
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