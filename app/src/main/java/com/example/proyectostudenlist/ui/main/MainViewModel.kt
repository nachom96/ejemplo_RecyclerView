package com.example.proyectostudenlist.ui.main

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.Repository
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.ui.utils.Event

//1º
private const val STATE_FILTER: String = "STATE_FILTER"

class MainViewModel(private val repository: Repository,
                    private val application: Application,
                    savedStateHandle: SavedStateHandle) : ViewModel() {

    private val legalAgeOnly: MutableLiveData<Boolean> =
        savedStateHandle.getLiveData(STATE_FILTER, false)

    var students: LiveData<List<Student>> = legalAgeOnly.switchMap {
        if (it) {
            repository.queryLegalAgeStudents()
        } else {
            repository.queryStudents()
        }
    }

    val filterText: LiveData<String> = legalAgeOnly.map { legalAgeOnly ->
        if (legalAgeOnly) {
            application.getString(R.string.main_all_students)
        } else {
            application.getString(R.string.main_legal_age_only)
        }
    }

    fun filterStudents() {
        legalAgeOnly.value = legalAgeOnly.value?.not() ?: true
    }


    private val _onShowMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val onShowMessage: LiveData<Event<String>>
        get() = _onShowMessage


    val lblEmptyViewVisibility: LiveData<Int> = students.map {
        if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

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

