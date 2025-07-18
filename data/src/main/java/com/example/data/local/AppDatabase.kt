package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.MovieDao
import com.example.data.local.entity.MovieEntity
import com.example.likeutils.data.Like
import com.example.likeutils.data.LikeDao
import com.example.myapplication.data.User
import com.example.myapplication.data.UserDao
import kotlin.jvm.java

const val USER_SESSION_PREFS = "user_session"
@Database(entities = [User::class, Like::class, MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun likeDao(): LikeDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
