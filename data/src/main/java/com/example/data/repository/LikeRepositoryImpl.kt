package com.example.data.repository

import com.example.data.local.entity.toEntity
import com.example.domain.model.Movie
import com.example.domain.repository.LikeRepository
import com.example.likeutils.data.Like
import com.example.likeutils.data.LikeDao
import com.example.myapplication.data.MovieDao

class LikeRepositoryImpl(
    private val likeDao: LikeDao,
    private val movieDao: MovieDao
): LikeRepository {

    override suspend fun likeMovie(movie: Movie, userEmail: String) {
        movieDao.insert(movie.toEntity())
        likeDao.insert(Like(itemId = movie.id, userEmail = userEmail))
    }

    override suspend fun isMovieLiked(movieId: Long, userEmail: String): Boolean {
        return likeDao.isItemLiked(movieId, userEmail)
    }

    override suspend fun unlikeMovie(movieId: Long, userEmail: String) {
        likeDao.deleteLike(movieId, userEmail)
    }
}
