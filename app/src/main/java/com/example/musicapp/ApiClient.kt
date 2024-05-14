package com.example.musicapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient.Builder


class ApiClient  {

    private var retrofit: Retrofit? = null



    fun getRetrofit(): Retrofit {
        if (retrofit == null) {

            val okHttpClient:OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/") // Adjust the base URL as needed
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!! // Use !! operator only if you're sure retrofit is initialized
    }
}
