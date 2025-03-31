package com.example.popcornchallenge.core.network

import com.example.popcornchallenge.core.network.model.DiscoverMovie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MDBRemoteDataSource(private val ktorClient: HttpClient) : MDBDataSource {
    override suspend fun getMovieList(page: Int): DiscoverMovie {
        val discoverResponse: DiscoverMovie = ktorClient.get("https://api.themoviedb.org/3/discover/movie?page=$page").body()
        return discoverResponse
    }
}