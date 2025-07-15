package com.example.domain.usecase

import com.example.domain.repository.LikeRepository

class UnlikeMovieUseCase(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(movieId: Long, userEmail: String) {
        likeRepository.unlikeMovie(movieId, userEmail)
    }
}
