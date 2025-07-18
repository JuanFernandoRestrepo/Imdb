package com.example.data.remote.mapper

import com.example.data.remote.ApiServices
import com.example.data.remote.model.MovieDto
import com.example.domain.model.Movie

class MovieMapper {
    fun map(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.original_title ?: "Sin título",
            description = dto.overview ?: "",
            imageUrl = "${ApiServices.IMAGE_URL}${dto.poster_path}"
        )
    }
    fun mapFromEntity(entity: com.example.data.local.entity.MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.original_title,
            description = entity.overview,
            imageUrl = entity.poster_path
        )
    }

}
