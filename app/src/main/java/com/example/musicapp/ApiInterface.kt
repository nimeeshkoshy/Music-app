package com.example.musicapp

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: 6a25921328msh17b7ce9048e23cep163d2bjsna851d4fb26cb",
             "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")

    fun getData(@Query("q") query: String): retrofit2.Call<Root>

}