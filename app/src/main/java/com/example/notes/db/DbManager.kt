package com.example.notes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.notes.model.noteModel

class DbManager(context: Context) {
    private val dbHelper = DbHelper(context)
    private var db: SQLiteDatabase? = null


    //    Function for open DB
    fun open() {
        db = dbHelper.writableDatabase
    }


    //    Function for close DB
    fun close(){
        dbHelper.close()
    }


    //    Function for write to DB
    fun writeToDb(title:String, content:String){
        val values = ContentValues().apply {
            put(DbNames.COLUMN_NAME_TITLE,title)
            put(DbNames.COLUMN_NAME_CONTENT,content)
        }
        db?.insert(DbNames.TABLE_NAME, null, values)
    }


    //    Function for search in DB
    @SuppressLint("Range")
    fun readSpecificFromDb(whatSearch : String): ArrayList<noteModel>? {
        val notesList = ArrayList<noteModel>()

        val selection = "${DbNames.COLUMN_NAME_TITLE} like ?"
        val cursor = db?.query(DbNames.TABLE_NAME, null,selection, arrayOf("%$whatSearch%"),null,
            null,null)
        if (cursor != null){
            while (cursor.moveToNext()){
                val dataId = cursor.getInt(cursor.getColumnIndex(DbNames.COLUMN_NAME_ID))
                val dataTitle = cursor.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_TITLE))
                val dataContent = cursor.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_CONTENT))
                notesList.add(noteModel(dataId, dataTitle,dataContent))
            }
            cursor.close()
            return notesList
        }
        return null
    }


    //    Function for read all from DB
    @SuppressLint("Range")
    fun readAllFromDb(): ArrayList<noteModel>? {
        val notesList = ArrayList<noteModel>()

        val cursor = db?.query(DbNames.TABLE_NAME, null,null,null,null,
            null,null)
        if (cursor != null){
            while (cursor.moveToNext()){
                val dataId = cursor.getInt(cursor.getColumnIndex(DbNames.COLUMN_NAME_ID))
                val dataTitle = cursor.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_TITLE))
                val dataContent = cursor.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_CONTENT))
                notesList.add(noteModel(dataId, dataTitle,dataContent))
            }
            cursor.close()
            return notesList
        }
        return null
    }


    //    Function for delete element from DB
    fun deleteIfExists(id: Int) {
        db?.delete(DbNames.TABLE_NAME, "${DbNames.COLUMN_NAME_ID} = ?", arrayOf(id.toString()))
    }
}