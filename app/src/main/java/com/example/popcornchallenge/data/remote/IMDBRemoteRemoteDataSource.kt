package com.example.popcornchallenge.data.remote

import com.example.popcornchallenge.data.remote.model.DiscoverMovie
import com.example.popcornchallenge.data.remote.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

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

    override suspend fun getMovie(id: Int): MovieResponse {
        val response = ktorClient.get("${BASE_URL}movie/${id}")

        return when (response.status.value) {
            200 -> {
                val movieResponse: MovieResponse = response.body()
                return movieResponse
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