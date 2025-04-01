package com.example.popcornchallenge.domain.usecase

import androidx.paging.PagingData
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.repository.IMovieDiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDiscoverUseCase @Inject constructor(
    private val movieRepository: IMovieDiscoverRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return movieRepository.getMovies()
    }
}