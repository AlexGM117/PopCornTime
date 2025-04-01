package com.example.popcornchallenge.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.popcornchallenge.R
import com.example.popcornchallenge.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun ItemDetailScreen(innerPadding: PaddingValues, id: Int, viewModel: MovieDetailsViewModel) {

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(id)
    }

    val movieState by viewModel.movieState.collectAsState()

    movieState.data?.let {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w342${it.posterPath}")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(width = 150.dp, height = 200.dp),
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = it.title,
                        fontSize = 20.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = it.overview,
                        fontSize = 14.sp
                    )
                }
            }
            it.genres.takeIf { it.isNotEmpty() }.let { genres ->
                Text(
                    modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                    text = "Genres: ${genres?.joinToString()}",
                    fontSize = 16.sp
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Release date: ${it.releaseDate}",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = PIXEL_4_XL
)
private fun ItemDetailScreenPreview() {

}