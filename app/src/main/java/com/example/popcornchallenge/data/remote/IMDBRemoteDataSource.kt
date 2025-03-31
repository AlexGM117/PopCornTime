package com.example.popcornchallenge.data.remote

import com.example.popcornchallenge.data.remote.model.DiscoverMovie

interface IMDBRemoteDataSource {
    suspend fun getMovieList(page: Int): DiscoverMovie
}