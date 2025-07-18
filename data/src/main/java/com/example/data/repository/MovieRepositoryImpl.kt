package com.example.data.repository

import com.example.data.datasources.local.MovieLocalDataSource
import com.example.data.datasources.remote.MovieRemoteDataSource
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.data.remote.mapper.MovieMapper

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getPopularMovies().map { mapper.map(it) }
    }

    override suspend fun likeMovie(movieId: Long) {
        localDataSource.likeMovie(movieId)
    }

    override suspend fun unlikeMovie(movieId: Long) {
        localDataSource.unlikeMovie(movieId)
    }

    override suspend fun isMovieLiked(movieId: Long): Boolean {
        return localDataSource.isMovieLiked(movieId)
    }

    override suspend fun getLikedMovieIds(): List<Long> {
        return localDataSource.getLikedMovieIds()
    }
    override suspend fun getMoviesByIds(ids: List<Long>): List<Movie> {
        return remoteDataSource.getPopularMovies().map { mapper.map(it) }
    }

}
