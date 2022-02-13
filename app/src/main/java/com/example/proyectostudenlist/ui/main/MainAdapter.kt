package com.example.proyectostudenlist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectostudenlist.R
import com.example.proyectostudenlist.data.entity.Student
import com.example.proyectostudenlist.databinding.MainItemBinding

//1º
class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    //3º
    private var data: List<Student> = emptyList()

    //10º
    private var onItemClickListener: OnItemClickListener? = null

    //11º
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    //12º
    fun getItem(position: Int) = data[position]

    //5º
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }
    //6º
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    //4º
    override fun getItemCount(): Int = data.size

    //8º
    fun submitList(newData: List<Student>){
        data = newData
        notifyDataSetChanged()
    }


    //2º //13º, en OnItemClickListener de los apuntes
    inner class ViewHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition // Probar con bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }

        //7º
        fun bind(student: Student){
            binding.lblName.text = student.name
            binding.lblAge.text = student.age.toString()
        }

    }

    //9º Después del paso 8 del MainActivity
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}