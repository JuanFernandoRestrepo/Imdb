package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM movie WHERE id = :movieId)")
    suspend fun isMovieLiked(movieId: String): Boolean

    @Query("SELECT itemId FROM liked_items WHERE userEmail = :email")
    suspend fun getLikedMovieIds(email: String): List<Long>

    @Query("SELECT * FROM movie WHERE id IN (:ids)")
    suspend fun getMoviesByIds(ids: List<Long>): List<MovieEntity>
}