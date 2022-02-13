package com.example.proyectostudenlist.data

import com.example.proyectostudenlist.data.entity.Student

interface Repository {

    fun queryStudents(): List<Student>
    fun deleteStudent(student: Student): Boolean

}