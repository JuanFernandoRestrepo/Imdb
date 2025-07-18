package com.example.data.datasources.local

import com.example.data.local.entity.MovieEntity

interface MovieLocalDataSource {
    suspend fun likeMovie(movieId: Long)
    suspend fun unlikeMovie(movieId: Long)
    suspend fun isMovieLiked(movieId: Long): Boolean
    suspend fun getLikedMovieIds(): List<Long>
    suspend fun getMoviesByIds(ids: List<Long>): List<MovieEntity>

}

