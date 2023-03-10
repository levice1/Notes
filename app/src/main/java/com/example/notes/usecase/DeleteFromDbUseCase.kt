package com.example.notes.usecase

import android.content.Context
import com.example.dbsqlite.db.dbManager

class DeleteFromDbUseCase (val id : Int, context: Context) {
    val dbManager = dbManager(context)
    fun delete(){
        dbManager.open()
        dbManager.deleteIfExists(id)
        dbManager.close()
    }
}