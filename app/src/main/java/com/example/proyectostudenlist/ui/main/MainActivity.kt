package com.example.proyectostudenlist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.*
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.DefaultRepository
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.databinding.MainActivityBinding
import com.example.proyectostudenlist.ui.utils.doOnSwiped
import com.example.proyectostudenlist.ui.utils.observeEvent
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //1º
    private val binding: MainActivityBinding by lazy{
        MainActivityBinding.inflate(layoutInflater)
    }

    private val listAdapter: MainAdapter = MainAdapter().apply {
        setOnItemClickListener { showStudent(currentList[it]) }
    }

    private fun showStudent(student: Student) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show()
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(DefaultRepository, application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.students.observe(this) { showStudents(it) }
        viewModel.onShowMessage.observeEvent(this) {
            showMessage(it)
        }
    }

    private fun showStudents(students: List<Student>) {
        listAdapter.submitList(students)
        binding.lblEmptyView.visibility =
            if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    private fun showMessage(it: String) {
        Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        setupRecyclerView()
        binding.btnDeleteAll.setOnClickListener {
            viewModel.deleteAllStudents()
        }
    }

    private fun setupRecyclerView() {
        binding.lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = listAdapter
            doOnSwiped { viewHolder, _ ->
                deleteStudent(listAdapter.currentList[viewHolder.bindingAdapterPosition])
                // Si no funciona, usar adapterPosition de los apuntes (deprecado)
            }
        }
    }

    private fun deleteStudent(student: Student) {
        viewModel.deleteStudent(student)
        showUndo(student)
    }

    private fun showUndo(student: Student) {
        Snackbar.make(binding.root, getString(R.string.main_student_deleted), // El main_student_deleted usa el % para mostrar el estudiante
            Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.main_undo)) { addStudent(student) }
            .show()
    }

    private fun addStudent(student: Student) {
        viewModel.addStudent(student)
    }
}