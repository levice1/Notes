package com.example.notes.usecase

import android.content.Context
import com.example.notes.db.DbManager
import com.example.notes.model.noteModel

class GetAllFromDbUseCase (context: Context, private val searchText: String = "") {
    private val dbManager = DbManager(context)
    fun get() : ArrayList<noteModel>? {
        dbManager.open()
        val data = dbManager.readAllFromDb()
        dbManager.close()
        if (data.isNullOrEmpty()) return null
        else return data
    }
}