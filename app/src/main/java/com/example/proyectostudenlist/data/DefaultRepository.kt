package com.example.proyectostudenlist.data

import com.example.proyectostudenlist.data.entity.Student

object DefaultRepository : Repository{

    private var students: List<Student> = listOf(
        Student(1, "Nacho", 25),
        Student(2, "Noe", 23),
        Student(3, "Duende", 23),
        Student(4, "Pablos", 23),
        Student(5, "Piedras", 23),
        Student(6, "Elena", 23),
    )

    override fun queryStudents(): List<Student> = students.toList()

    override fun deleteStudent(student: Student): Boolean {
        val oldSize = students.size
        students = students - student
        return students.size < oldSize
    }


}