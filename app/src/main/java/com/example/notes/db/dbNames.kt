package com.example.notes.db

import android.provider.BaseColumns

object dbNames {
    const val TABLE_NAME = "main_table"
    const val COLUMN_NAME_ID = "_id"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "firstDB.db"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT)"
}