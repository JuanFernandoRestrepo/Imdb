package com.example.data.datasources.remote

import com.example.data.remote.model.MovieDto

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): List<MovieDto>
}