package com.example.notes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbHelper(context: Context): SQLiteOpenHelper(context, dbNames.DATABASE_NAME,
    null, dbNames.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
//        Creating DB
        db?.execSQL(dbNames.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        Delete and creating new DB
        db?.execSQL(dbNames.DELETE_TABLE)
        onCreate(db)
    }
}