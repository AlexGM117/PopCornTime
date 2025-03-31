package com.example.popcornchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.popcornchallenge.core.data.MovieDiscoverRepository
import com.example.popcornchallenge.core.network.MDBRemoteDataSource
import com.example.popcornchallenge.core.network.ktor.KtorNetworkClient

import com.example.popcornchallenge.presentation.theme.PopCornChallengeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val repository = MovieDiscoverRepository(
        network = MDBRemoteDataSource(ktorClient = KtorNetworkClient.client)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PopCornChallengeTheme {
                LaunchedEffect(Unit) {
                    GlobalScope.launch(Dispatchers.IO) {
                        repository.getMovies(1).collect {

                        }
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PopCornChallengeTheme {
        Greeting("Android")
    }
}