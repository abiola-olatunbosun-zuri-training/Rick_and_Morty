package com.example.rickandmorty.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rickandmortyapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface RickAndMortyApiService{
    @GET("character")
    suspend fun getRickAndMortyApi(): Response<Rockandmorty>
}


object RockAndMortyApi{
    val retrofitService : RickAndMortyApiService by lazy{
        retrofit.create(RickAndMortyApiService::class.java)
    }
}