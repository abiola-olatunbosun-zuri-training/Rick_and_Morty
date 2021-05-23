package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.network.MainActivityViewModel
import com.example.rickandmorty.network.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var  viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rockAndMortyRepository = RockAndMortyRepository()
        val viewModelFactory = MainActivityViewModelFactory(rockAndMortyRepository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

        val adapter = MainAcitivityAdapter()
        binding.rickMorkyRv.adapter = adapter


        viewModel.morkandrocky.observe(this, Observer {
            adapter.differ.submitList(it.results)
        })
    }
}