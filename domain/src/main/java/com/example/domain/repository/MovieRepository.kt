package com.example.domain.repository

import com.example.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun likeMovie(movieId: Long)
    suspend fun unlikeMovie(movieId: Long)
    suspend fun isMovieLiked(movieId: Long): Boolean
    suspend fun getLikedMovieIds(): List<Long>
    suspend fun getMoviesByIds(ids: List<Long>): List<Movie>

}

