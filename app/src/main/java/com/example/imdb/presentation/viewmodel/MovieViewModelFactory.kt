package com.example.imdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.LikeMovieUseCase
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.domain.usecase.UnlikeMovieUseCase
import com.example.domain.usecase.IsMovieLikedUseCase

class MovieViewModelFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val likeMovieUseCase: LikeMovieUseCase,
    private val unlikeMovieUseCase: UnlikeMovieUseCase,
    private val isMovieLikedUseCase: IsMovieLikedUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(
            getPopularMoviesUseCase,
            likeMovieUseCase,
            unlikeMovieUseCase,
            isMovieLikedUseCase
        ) as T
    }
}
