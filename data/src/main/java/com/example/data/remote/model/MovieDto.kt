package com.example.data.remote.model

data class MoviesDto(
    val page: Int?,
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Long,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?
)
