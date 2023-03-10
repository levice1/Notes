package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dbsqlite.db.dbManager
import com.example.notes.databinding.ActivityEditBinding
import com.example.notes.model.IntentNames
import com.example.notes.model.noteModel
import com.example.notes.usecase.DeleteFromDbUseCase
import com.example.notes.usecase.SaveOrUpdateInDbUseCase

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    val dbManager = dbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val inputData = getAndSetIntent()
        if (inputData != null) initDeleteButton(inputData.id)
        initSaveButton(inputData)
    }


    // функц инициализации кнопки сохрвнить
    private fun initSaveButton(inputData : noteModel?){
        binding.btnSave.setOnClickListener {
            SaveOrUpdateInDbUseCase(
                inputData?.id,
                binding.plainTxtTitle.text.toString(),
                binding.plainTxtDescription.text.toString(),
                this
            ).write()
            finish()
        }
    }

    // функц инициализации кнопки удалить
    private fun initDeleteButton(id: Int){
        VisibilitySetting().setVisibilityForDelBtn(binding)
        binding.btnDelete.setOnClickListener {
            DeleteFromDbUseCase(id,this).delete()
            finish()
        }
    }

    // функц получения и установки во вью данных переданых через интент
    private fun getAndSetIntent() : noteModel? {
        val i = intent
        // если в интенте что-то есть (hasExtra)
        if (i.hasExtra(IntentNames.ID)){
            // установка данных во вью, и возврат обьекта noteModel
            binding.plainTxtTitle.setText(i.getStringExtra(IntentNames.TITLE))
            binding.plainTxtDescription.setText(i.getStringExtra(IntentNames.DESCRIPTION))
            return noteModel( i.getIntExtra(IntentNames.ID,998) ,
                              i.getStringExtra(IntentNames.TITLE).toString(),
                              i.getStringExtra(IntentNames.DESCRIPTION).toString()
                            )
            //если пусто то возврат null
        } else return null
    }
}