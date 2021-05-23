package com.example.rickandmorty.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.RockAndMortyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: RockAndMortyRepository
) :ViewModel(){
    private val viewModel = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModel)

    private val _morkandrocky = MutableLiveData<Rockandmorty>()
    val morkandrocky:LiveData<Rockandmorty>
    get() = _morkandrocky

init {
    getRockyAndMortyDetails()
}
    override fun onCleared() {
        super.onCleared()
        viewModel.cancel()
    }

    private fun getRockyAndMortyDetails(){
        viewModelScope.launch {
            try {
                val listResult = repository.getRickAndMortyInfo()
                _morkandrocky.value = listResult.body()!!
            }catch (t: Throwable){
               _morkandrocky.value = Rockandmorty(Info(0,"",0,""), listOf())
            }

        }
//        uiScope.launch {
//            try {
//                val listResult = RockAndMortyApi.retrofitService.getRickAndMortyApi()
//                _morkandrocky.value = listResult.body()!!
//            }catch (t: Throwable){
//                _morkandrocky.value = Rockandmorty(Info(0,"",0,""), listOf())
//            }
//        }
    }
}