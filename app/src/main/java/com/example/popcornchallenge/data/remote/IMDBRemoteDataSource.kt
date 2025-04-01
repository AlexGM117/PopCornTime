package com.example.popcornchallenge.data.remote

import com.example.popcornchallenge.data.remote.model.DiscoverMovie
import com.example.popcornchallenge.data.remote.model.MovieResponse

interface IMDBRemoteDataSource {
    suspend fun getMovieList(page: Int): DiscoverMovie
    suspend fun getMovie(id: Int): MovieResponse
}