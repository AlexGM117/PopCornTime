package com.example.popcornchallenge.domain.usecase

import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.repository.IMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: IMovieDetailsRepository
) {
    operator fun invoke(id: Int): Flow<Movie> =
        repository.getMovie(id)
}
