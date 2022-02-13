package com.example.proyectostudenlist.data

import androidx.lifecycle.LiveData
import com.example.proyectostudenlist.data.entity.Student
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

object DefaultRepository : Repository{

    private val students: MutableLiveData<List<Student>> =
        MutableLiveData(
            listOf(
                Student(1, "Nacho", 25),
                Student(2, "Noe", 23),
                Student(3, "Duende3", 23),
                Student(4, "Piedad", 23),
                Student(5, "Elena", 23),
                Student(6, "Pablo", 23),


            )
        )

    override fun queryAllStudents(): LiveData<List<Student>> =
        Transformations.map(students) { list -> list.sortedBy {it.name} }

    override fun deleteStudent(student: Student): Boolean {
        val newList = students.value!!.filter { it.id != student.id }
        val removed = newList.size != students.value!!.size
        students.value = newList
        return removed
    }

    override fun addStudent(student: Student): Boolean {
        val newList = students.value!! + student
        val added = newList.size != students.value!!.size
        students.value = newList
        return added
    }

    override fun deleteAllStudents() {
        students.value = emptyList()
    }




}