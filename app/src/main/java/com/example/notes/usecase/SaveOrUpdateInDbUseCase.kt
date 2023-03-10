package com.example.notes.usecase

import android.content.Context
import android.util.Log
import com.example.dbsqlite.db.dbManager

class SaveOrUpdateInDbUseCase(val id: Int? = null, val title:String, val content:String, context: Context) {
    val dbManager = dbManager(context)

    fun write(){
        dbManager.open()
        val dbData = dbManager.readAllFromDb()
        if (dbData != null){
        dbData.forEach{
            Log.d("TestMsg", "цикл")
            if (it.id == id){
                dbManager.deleteIfExists(it.id)
                dbManager.writeToDb(title,content)
                Log.d("TestMsg", "(it.title == title)")
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