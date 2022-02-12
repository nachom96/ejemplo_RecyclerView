package com.example.proyectostudenlist.data

import com.example.proyectostudenlist.data.entity.Student

object DefaultRepository : Repository{

    private val students: List<Student> = listOf(
        Student(1, "Nacho", 25),
        Student(2, "Noe", 23),
        Student(3, "Duende", 23),
    )

    override fun queryStudents(): List<Student> = students.toList()


}