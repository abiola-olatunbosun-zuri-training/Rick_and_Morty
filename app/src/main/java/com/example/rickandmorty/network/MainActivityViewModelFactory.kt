package com.example.rickandmorty.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.RockAndMortyRepository

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(
    private val repository: RockAndMortyRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}