package com.example.notes.usecase

import android.content.Context
import com.example.notes.db.DbManager

class DeleteFromDbUseCase (val id : Int, context: Context) {
    private val dbManager = DbManager(context)
    fun delete(){
        dbManager.open()
        dbManager.deleteIfExists(id)
        dbManager.close()
    }
}