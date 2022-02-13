package com.example.proyectostudenlist.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.Repository
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.ui.utils.Event

//1º
class MainViewModel(private val repository: Repository,
                    private val application: Application) : ViewModel() {

    private val _onShowMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val onShowMessage: LiveData<Event<String>>
        get() = _onShowMessage

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
        } else {
            _onShowMessage.value =
                Event(application.getString(R.string.main_no_students_to_delete))
        }
    }

}

