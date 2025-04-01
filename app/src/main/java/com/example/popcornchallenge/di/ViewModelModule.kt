package com.example.popcornchallenge.di

import com.example.popcornchallenge.data.remote.IMDBRemoteDataSource
import com.example.popcornchallenge.data.remote.IMDBRemoteRemoteDataSource
import com.example.popcornchallenge.domain.repository.IMovieDetailsRepository
import com.example.popcornchallenge.domain.repository.IMovieDiscoverRepository
import com.example.popcornchallenge.domain.repository.MovieDetailsRepository
import com.example.popcornchallenge.domain.repository.MovieDiscoverRepository
import com.example.popcornchallenge.domain.usecase.GetMovieDetailUseCase
import com.example.popcornchallenge.domain.usecase.GetMovieDiscoverUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @ViewModelScoped
    fun bindRemoteRepository(remoteRepository: MovieDiscoverRepository): IMovieDiscoverRepository

    @Binds
    @ViewModelScoped
    fun bindRemoteDataSource(remoteDataSource: IMDBRemoteRemoteDataSource): IMDBRemoteDataSource

    @Binds
    @ViewModelScoped
    fun bindRemoteDetailRepository(remoteRepository: MovieDetailsRepository): IMovieDetailsRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideGetMovieDiscoverUseCase(
            remoteRepository: MovieDiscoverRepository
        ) = GetMovieDiscoverUseCase(remoteRepository)
        @Provides
        @ViewModelScoped
        fun provideGetMovieDetailUseCase(
            remoteRepository: IMovieDetailsRepository
        ) = GetMovieDetailUseCase(remoteRepository)
    }
}
