package com.example.popcornchallenge.data.remote

import com.example.popcornchallenge.data.remote.model.DiscoverMovie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class IMDBRemoteRemoteDataSource(private val ktorClient: HttpClient) : IMDBRemoteDataSource {
    @Suppress("NAME_SHADOWING", "UNREACHABLE_CODE")
    override suspend fun getMovieList(page: Int): DiscoverMovie {

        val response = ktorClient.get(DISCOVER_MOVIE) {
            url {
                parameters.append("page", page.toString())
            }
        }
        //val discoverResponse: DiscoverMovie = ktorClient.get(DISCOVER_MOVIE).body()

        return when (response.status.value) {
            200 -> {
                val discoverResponse: DiscoverMovie = response.body()
                return discoverResponse
            }
            else -> {
                throw Exception(response.status.description)
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val DISCOVER_MOVIE = "${BASE_URL}discover/movie"
    }
}