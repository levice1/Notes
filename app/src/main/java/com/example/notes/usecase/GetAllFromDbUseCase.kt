package com.example.notes.usecase

import android.content.Context
import com.example.dbsqlite.db.dbManager
import com.example.notes.model.noteModel

class GetAllFromDbUseCase (context: Context) {
    val dbManager = dbManager(context)
    fun get() : ArrayList<noteModel>? {
        dbManager.open()
        val data = dbManager.readAllFromDb()
        dbManager.close()
        if (data.isNullOrEmpty()) return null
        else return data
    }
}