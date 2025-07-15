package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Long,
    val original_title: String,
    val overview: String,
    val poster_path: String
)
fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        original_title = this.title,
        overview = this.description,
        poster_path = this.imageUrl
    )
}

