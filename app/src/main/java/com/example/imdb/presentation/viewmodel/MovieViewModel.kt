package com.example.imdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.domain.usecase.LikeMovieUseCase
import com.example.domain.usecase.UnlikeMovieUseCase
import com.example.domain.usecase.IsMovieLikedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val likeMovieUseCase: LikeMovieUseCase,
    private val unlikeMovieUseCase: UnlikeMovieUseCase,
    private val isMovieLikedUseCase: IsMovieLikedUseCase
) : ViewModel() {

    private val _likedMovies = MutableStateFlow<Set<Long>>(emptySet())
    val likedMovies: StateFlow<Set<Long>> = _likedMovies


    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = getPopularMoviesUseCase()
        }
    }

    fun checkIfLiked(movieId: Long, userEmail: String) {
        viewModelScope.launch {
            val isLiked = isMovieLikedUseCase(movieId, userEmail)
            _likedMovies.value = _likedMovies.value.toMutableSet().apply {
                if (isLiked) add(movieId) else remove(movieId)
            }
        }
    }

    fun toggleLike(movie: Movie, userEmail: String) {
        viewModelScope.launch {
            val isLiked = isMovieLikedUseCase(movie.id, userEmail)
            if (isLiked) {
                unlikeMovieUseCase(movie.id, userEmail)
            } else {
                likeMovieUseCase(movie, userEmail)
            }
            checkIfLiked(movie.id, userEmail)
        }
    }
}
