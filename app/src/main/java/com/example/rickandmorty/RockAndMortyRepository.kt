package com.example.rickandmorty

import com.example.rickandmorty.network.RockAndMortyApi

class RockAndMortyRepository (){
    suspend fun getRickAndMortyInfo()= RockAndMortyApi.retrofitService.getRickAndMortyApi()
}