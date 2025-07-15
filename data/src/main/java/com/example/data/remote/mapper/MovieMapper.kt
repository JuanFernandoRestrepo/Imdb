package com.example.data.remote.mapper

import com.example.data.remote.ApiServices
import com.example.data.remote.model.MovieDto
import com.example.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.original_title ?: "Sin título",
        description = this.overview ?: "",
        imageUrl = "${ApiServices.IMAGE_URL}${this.poster_path}"
    )
}
