package com.example.popcornchallenge.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.data.remote.IMDBRemoteDataSource
import com.example.popcornchallenge.presentation.ui.MovieDiscoverPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDiscoverRepository @Inject constructor(
    private val remoteDataSource: IMDBRemoteDataSource
) : IMovieDiscoverRepository {
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MovieDiscoverPagingSource(remoteDataSource)
            }
        ).flow
    }
}
