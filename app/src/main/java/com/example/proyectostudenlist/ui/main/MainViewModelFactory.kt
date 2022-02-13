package com.example.proyectostudenlist.ui.main

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.proyectostudenlist.data.Repository

//1º (Se imlementa el método del error)
class MainViewModelFactory(
    private val repository: Repository,
    private val application: Application,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, application, handle) as T
        } else {
            throw IllegalArgumentException("Wrong viewModel class passed")
        }
    }

}