package com.example.data.repository

import com.example.data.remote.ApiInterface
import com.example.data.remote.mapper.toDomain
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: ApiInterface
) : MovieRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        val response = api.getMovies()
        return response.results.map { it.toDomain() }
    }
}
