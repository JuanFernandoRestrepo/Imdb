package com.example.myapplication.data

import androidx.room.Embedded
import com.example.data.local.entity.MovieEntity

data class MovieWithLikes(
    @Embedded val movie: MovieEntity,
    val likeCount: Int
)