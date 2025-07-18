package com.example.domain.usecase

import com.example.domain.repository.LikeRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.model.MovieWithLikes
import kotlin.collections.map
import kotlin.collections.mapNotNull

class GetTopLikedMoviesUseCase(
    private val likeRepository: LikeRepository,
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<MovieWithLikes> {
        val topLikes = likeRepository.getTopLikedItems()
        val movieIds = topLikes.map { it.itemId }

        val movies = movieRepository.getMoviesByIds(movieIds)
        val movieMap = movies.associateBy { it.id }

        return topLikes.mapNotNull { topLike ->
            movieMap[topLike.itemId]?.let { movie ->
                MovieWithLikes(movie, topLike.likeCount)
            }
        }.sortedByDescending { it.likeCount }
    }
}
