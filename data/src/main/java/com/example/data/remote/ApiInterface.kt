package com.example.data.remote

import com.example.data.remote.model.MoviesDto
import retrofit2.http.GET

interface ApiInterface {
    @GET("top_rated?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getMovies(): MoviesDto
}