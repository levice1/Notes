package com.example.notes.usecase

import android.content.Context
import com.example.notes.db.DbManager

class SaveOrUpdateInDbUseCase(val id: Int? = null, private val title:String, private val content:String, context: Context) {
    private val dbManager = DbManager(context)

    fun write(){
        dbManager.open()
        val dbData = dbManager.readAllFromDb()
        if (dbData != null){
        dbData.forEach{
            if (it.id == id){
                dbManager.deleteIfExists(it.id)
                dbManager.writeToDb(title,content)
                dbManager.close()
                return
            }
        }
        dbManager.writeToDb(title,content)
        dbManager.close()
    } else {
        dbManager.writeToDb(title,content)
        dbManager.close()
    }
}}