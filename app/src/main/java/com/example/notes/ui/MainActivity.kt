package com.example.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.db.DbManager
import com.example.notes.adapter.recViewAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.usecase.GetAllFromDbUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: recViewAdapter  // присвоение адаптера к переменной
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val dbManager = DbManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecView()
    }


    override fun onResume() {
        super.onResume()
        val dbData = GetAllFromDbUseCase(this).get()
        if (dbData.isNullOrEmpty()) VisibilitySetting().setVisibilityIsEmptyDB(binding)
        else {
            adapter.setList(dbData)
            VisibilitySetting().setVisibilityIsNotEmptyDB(binding)
        }
        binding.btnAdd.setOnClickListener{
            val i = Intent(this, EditActivity::class.java)
            startActivity(i)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        dbManager.close()
    }


    private fun initRecView(){
        recyclerView = binding.recVew // присвоение самого рецайклер вью
        adapter = recViewAdapter(this) // присвоение адаптера к переменной
        recyclerView.adapter = adapter // добавление адаптера к рециклер вью
    }


}