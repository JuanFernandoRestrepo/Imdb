package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.model.TopLikedItem

interface LikeRepository {
    suspend fun likeMovie(movie: Movie, userEmail: String)
    suspend fun isMovieLiked(movieId: Long, userEmail: String): Boolean
    suspend fun unlikeMovie(movieId: Long, userEmail: String)
    suspend fun getTopLikedItems(): List<TopLikedItem>

}
