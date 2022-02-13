package com.example.proyectostudenlist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.databinding.MainItemBinding

//1º
class MainAdapter : ListAdapter<Student, MainAdapter.ViewHolder>(StudentDiffCallback) {

    private var data: List<Student> = emptyList()

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition // Si no funciona, usar adapterPosition de los apuntes (deprecado
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(student: Student){
            binding.lblName.text = student.name
            binding.lblAge.text = student.age.toString()
        }

    }

    object StudentDiffCallback : DiffUtil.ItemCallback<Student>() {

        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.age == newItem.age

    }


}

typealias OnItemClickListener = (position: Int) -> Unit