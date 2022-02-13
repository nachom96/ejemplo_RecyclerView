package com.example.proyectostudenlist.data

import androidx.lifecycle.LiveData
import com.example.proyectostudenlist.data.entity.Student

interface Repository {

    fun queryAllStudents(): LiveData<List<Student>>
    fun deleteStudent(student: Student): Boolean
    fun addStudent(student: Student): Boolean
    fun deleteAllStudents()


}