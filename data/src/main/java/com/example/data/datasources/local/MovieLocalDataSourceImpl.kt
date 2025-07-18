package com.example.data.datasources.local

import com.example.data.local.SessionManager
import com.example.data.local.dao.MovieDao
import com.example.data.local.entity.MovieEntity
import com.example.likeutils.data.Like
import com.example.likeutils.data.LikeDao


class MovieLocalDataSourceImpl(
    private val likeDao: LikeDao,
    private val movieDao: MovieDao,
    private val sessionManager: SessionManager
) : MovieLocalDataSource {

    override suspend fun likeMovie(movieId: Long) {
        val email = sessionManager.getUserEmail()
        likeDao.insert(Like(movieId, email.toString()))
    }

    override suspend fun unlikeMovie(movieId: Long) {
        val email = sessionManager.getUserEmail()
        likeDao.deleteLike(movieId, email.toString())
    }

    override suspend fun isMovieLiked(movieId: Long): Boolean {
        val email = sessionManager.getUserEmail()
        return likeDao.isItemLiked(movieId, email.toString())
    }

    override suspend fun getLikedMovieIds(): List<Long> {
        val email = sessionManager.getUserEmail()
        return likeDao.getLikesByUser(email.toString()).map { it.itemId }
    }
    override suspend fun getMoviesByIds(ids: List<Long>): List<MovieEntity> {
        return movieDao.getMoviesByIds(ids)
    }

}
