package com.example.imdb.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.model.MovieWithLikes
import com.example.domain.usecase.GetTopLikedMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopLikedMoviesUseCase: GetTopLikedMoviesUseCase
) : ViewModel() {

    private val _topMovies = MutableLiveData<List<MovieWithLikes>>()
    val topMovies: LiveData<List<MovieWithLikes>> = _topMovies

    fun loadTopLikedMovies() {
        viewModelScope.launch {
            val movies = getTopLikedMoviesUseCase()
            _topMovies.value = movies
        }
    }
}
