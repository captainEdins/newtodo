package com.example.todo.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class db_Helper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_CAT + " TEXT,"
                + COLUMN_PLACE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                +COLUMN_ABOUT + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addItem(c:String, p:String, t:String, a:String) {
        val values = ContentValues()
        values.put(COLUMN_CAT, c)
        values.put(COLUMN_PLACE, p)
        values.put(COLUMN_TIME, t)
        values.put(COLUMN_ABOUT, a)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    fun getAllPerson(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    fun getAllBusiness(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_CAT = business", null)
    }
    ///my items naming
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Todo_Data_new.db"
        val TABLE_NAME = "todo"

        val COLUMN_PLACE = "place"
        val COLUMN_TIME = "time"
        val COLUMN_ABOUT = "about"
        val COLUMN_CAT = "cat"
    }
}