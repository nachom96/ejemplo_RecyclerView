package com.example.proyectostudenlist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.DefaultRepository
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    //1º
    private val binding: MainActivityBinding by lazy{
        MainActivityBinding.inflate(layoutInflater)
    }
    //2º //8º Después del paso 13 del MainAdapter
    private val listAdapter: MainAdapter = MainAdapter().apply {
        setOnItemClickListener(object: MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                showStudent(getItem(position))
            }

        })
    }

    // 9º Para añadir el ripple effect sobre los elementos, añadir
    // android:background="?android:attr/selectableItemBackground" en main_item (en clRoot)
    private fun showStudent(student: Student) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show()
    }


        //6º (Después de haber creado la factoría)
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(DefaultRepository)
    }

    //3º
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        observeViewModel()
    }

    //8º
    private fun observeViewModel() {
        viewModel.students.observe(this) { showStudents(it) }
    }

    //7º
    private fun showStudents(students: List<Student>) {
        listAdapter.submitList(students)
        binding.lblEmptyView.visibility =
            if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    //4º
    private fun setupViews() {
        setupRecyclerView()
    }
    //5º
    private fun setupRecyclerView() {
        binding.lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = listAdapter
        }
    }
}