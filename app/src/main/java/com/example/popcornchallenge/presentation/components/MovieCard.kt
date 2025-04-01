package com.example.popcornchallenge.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.popcornchallenge.R
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.presentation.theme.black40
import com.example.popcornchallenge.presentation.theme.white
import com.example.popcornchallenge.presentation.viewmodel.DiscoverEvent

@Composable
fun MovieCard(movie: Movie, onEvent: (DiscoverEvent) -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onEvent.invoke(DiscoverEvent.NavigateDetail(movie.id)) }
            .wrapContentSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(black40)
                .align(Alignment.BottomCenter)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = movie.title,
                color = white,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
private fun MovieCardPreview() {
    MovieCard(Movie(
        id = 0,
        title = "Title",
        overview = "",
        posterPath = "",
        releaseDate = "",
        originalLanguage = "",
        originalTitle = "",
        genreIds = emptyList(),
        genres = emptyList()
    )){}
}
