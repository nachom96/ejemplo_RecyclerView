package com.example.proyectostudenlist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectostudenlist.data.Repository
import com.example.proyectostudenlist.data.entity.Student

//1º
class MainViewModel(repository: Repository) : ViewModel(){

    //2º
    private val _students: MutableLiveData<List<Student>> = MutableLiveData()
    val students: LiveData<List<Student>>
        get() = _students

    //3º
    init {
        _students.value = repository.queryStudents()
    }

}