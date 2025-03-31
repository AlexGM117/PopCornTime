package com.example.popcornchallenge.core.network

import com.example.popcornchallenge.core.network.model.DiscoverMovie

interface MDBDataSource {
    suspend fun getMovieList(page: Int): DiscoverMovie
}