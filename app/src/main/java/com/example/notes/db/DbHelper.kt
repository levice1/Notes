package com.example.notes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context): SQLiteOpenHelper(context, DbNames.DATABASE_NAME,
    null, DbNames.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
//        Creating DB
        db?.execSQL(DbNames.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        Delete and creating new DB
        db?.execSQL(DbNames.DELETE_TABLE)
        onCreate(db)
    }
}