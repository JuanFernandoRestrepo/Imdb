package com.example.imdb.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.datasources.local.MovieLocalDataSourceImpl
import com.example.data.datasources.remote.MovieRemoteDataSourceImpl
import com.example.data.local.AppDatabase
import com.example.data.local.SessionManager
import com.example.data.remote.ApiInterface
import com.example.data.remote.ApiServices
import com.example.data.remote.mapper.MovieMapper
import com.example.data.repository.LikeRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.usecase.GetTopLikedMoviesUseCase

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = AppDatabase.getDatabase(context)
        val sessionManager = SessionManager(context)

        val localDataSource = MovieLocalDataSourceImpl(
            likeDao = db.likeDao(),
            movieDao = db.movieDao(),
            sessionManager = sessionManager
        )

        val remoteDataSource = MovieRemoteDataSourceImpl(
            apiInterface = ApiServices.api
        )


        val movieRepository = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            mapper = MovieMapper()
        )

        val likeRepository = LikeRepositoryImpl(
            likeDao = db.likeDao(),
            movieDao = db.movieDao(),
            sessionManager = sessionManager
        )

        val getTopLikedMoviesUseCase = GetTopLikedMoviesUseCase(likeRepository, movieRepository)

        return HomeViewModel(getTopLikedMoviesUseCase) as T
    }
}
