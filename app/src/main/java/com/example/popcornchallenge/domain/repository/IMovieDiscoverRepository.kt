package com.example.popcornchallenge.domain.repository

import androidx.paging.PagingData
import com.example.popcornchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDiscoverRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}
