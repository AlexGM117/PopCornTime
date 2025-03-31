package com.example.popcornchallenge.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.popcornchallenge.core.network.ktor.KtorNetworkClient
import com.example.popcornchallenge.data.remote.IMDBRemoteRemoteDataSource
import com.example.popcornchallenge.domain.repository.MovieDiscoverRepository
import com.example.popcornchallenge.presentation.viewmodel.MovieDiscoverViewModel

@Composable
fun DiscoverMovieScreen(innerPadding: PaddingValues, onClick: () -> Unit) {
    MovieDiscoverViewModel(
        MovieDiscoverRepository(IMDBRemoteRemoteDataSource(KtorNetworkClient.client)
        )
    )
    DiscoverMovieScreenContent(innerPadding, onClick)
}

@Composable
fun DiscoverMovieScreenContent(innerPadding: PaddingValues, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        repeat(5) {
            Text(text = "Movie title", modifier = Modifier.clickable { onClick.invoke() })
            Spacer(modifier = Modifier.height(8.dp))
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
    DiscoverMovieScreenContent(PaddingValues(all = 16.dp)) {}
}