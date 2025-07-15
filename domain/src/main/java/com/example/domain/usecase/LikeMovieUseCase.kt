package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.LikeRepository

class LikeMovieUseCase(
    private val likeRepository: LikeRepository
) {
    suspend operator fun invoke(movie: Movie, userEmail: String) {
        likeRepository.likeMovie(movie, userEmail)
    }
}
