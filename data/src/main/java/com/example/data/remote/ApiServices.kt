package com.example.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServices {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

        private var retrofit: Retrofit? = null

        fun getApi(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}
