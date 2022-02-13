package com.example.proyectostudenlist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proyectostudenlist.data.Repository
import com.example.proyectostudenlist.data.entity.Student

//1º
class MainViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.queryAllStudents()

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

    fun addStudent(student: Student) {
        repository.addStudent(student)
    }

    fun deleteAllStudents() {
        if (students.value?.isNotEmpty() == true) {
            repository.deleteAllStudents()
        }
    }

}

