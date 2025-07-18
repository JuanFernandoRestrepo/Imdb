package com.example.data.datasources.remote

import com.example.data.remote.ApiInterface
import com.example.data.remote.model.MovieDto

class MovieRemoteDataSourceImpl(
    private val apiInterface: ApiInterface
) : MovieRemoteDataSource {

    override suspend fun getPopularMovies(): List<MovieDto> {
        return apiInterface.getMovies().results
    }
}
