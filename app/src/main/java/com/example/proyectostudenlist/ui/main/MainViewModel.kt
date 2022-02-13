package com.example.proyectostudenlist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectostudenlist.data.Repository
import com.example.proyectostudenlist.data.entity.Student

//1º
class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _students: MutableLiveData<List<Student>> = MutableLiveData()
    val students: LiveData<List<Student>> get() = _students

    init {
        queryStudents()
    }

    private fun queryStudents() {
        _students.value = repository.queryStudents()
    }

    fun deleteStudent(student: Student) {
        if (repository.deleteStudent(student)) {
            queryStudents()
        }
    }

}
