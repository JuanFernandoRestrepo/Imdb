package com.example.domain.usecase

import com.example.domain.repository.LikeRepository

class IsMovieLikedUseCase(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(movieId: Long, userEmail: String): Boolean =
        likeRepository.isMovieLiked(movieId, userEmail)

}
