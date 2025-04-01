package com.example.popcornchallenge.domain.usecase

import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.repository.IMovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUseCase(
    private val repository: IMovieDetailsRepository
) {
    operator fun invoke(id: Int): Flow<Movie> =
        repository.getMovie(id)
}
