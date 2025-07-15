package com.example.imdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.repository.LikeRepository
import com.example.myapplication.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val _likedMovies = MutableStateFlow<Set<Long>>(emptySet())
    val likedMovies: StateFlow<Set<Long>> = _likedMovies

    fun checkIfLiked(movieId: Long, userEmail: String) {
        viewModelScope.launch {
            val isLiked = likeRepository.isMovieLiked(movieId, userEmail)
            _likedMovies.value = _likedMovies.value.toMutableSet().apply {
                if (isLiked) add(movieId) else remove(movieId)
            }
        }
    }

    fun toggleLike(movie: Movie, userEmail: String) {
        viewModelScope.launch {
            val isLiked = likeRepository.isMovieLiked(movie.id, userEmail)
            if (isLiked) {
                likeRepository.unlikeMovie(movie.id, userEmail)
            } else {
                likeRepository.likeMovie(movie, userEmail)
            }
            val updated = _likedMovies.value.toMutableSet()
            if (isLiked) {
                updated.remove(movie.id)
            } else {
                updated.add(movie.id)
            }
            _likedMovies.value = updated
        }
    }



    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = getPopularMoviesUseCase()
        }
    }
}
