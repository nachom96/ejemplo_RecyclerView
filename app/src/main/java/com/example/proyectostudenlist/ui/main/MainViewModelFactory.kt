package com.example.proyectostudenlist.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectostudenlist.data.Repository

//1º (Se imlementa el método del error)
class MainViewModelFactory(val repository: Repository) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(repository) as T

}