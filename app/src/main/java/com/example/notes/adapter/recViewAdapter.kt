package com.example.notes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.ui.EditActivity
import com.example.notes.databinding.RecViewLayoutBinding
import com.example.notes.model.IntentNames
import com.example.notes.model.noteModel

class recViewAdapter(val context: Context) : RecyclerView.Adapter<recViewAdapter.ViewHolder>() {

    private var notesList = ArrayList<noteModel>()
    // в класс ViewHolder нужно передавать и контекст, для того чтобы запустить активити отсюда
    class ViewHolder(val binding: RecViewLayoutBinding, context: Context) : RecyclerView.ViewHolder(binding.root){

    }
    // в функцию onCreateViewHolder нужно передавать и контекст, для того чтобы запустить активити отсюда
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),context)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtTitle.text = notesList[position].title
        holder.binding.txtDescription.text = notesList[position].descriprion
        holder.itemView.setOnClickListener{ // слушатель нажатий для каждого ItemView
            // создание интента для перехода на новое активити. Для этого нужно передавать контекст в адаптер
            val i = Intent(context, EditActivity::class.java)
            // добавление в интент данных. Добавляю строками чтобы легче было передавать и принимать
            i.putExtra(IntentNames.ID, notesList[position].id)
            i.putExtra(IntentNames.TITLE, notesList[position].title)
            i.putExtra(IntentNames.DESCRIPTION, notesList[position].descriprion)
            // старт активити редактирования/создания и передача в него данных нажатого обьекта
            context.startActivity(i)
        }
    }

    fun setList(list: ArrayList<noteModel>){
        notesList.clear() // чистить чтобы данные не дублировались
        notesList = list
        notifyDataSetChanged()
    }
}