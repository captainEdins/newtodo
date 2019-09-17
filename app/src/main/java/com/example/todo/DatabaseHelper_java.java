package com.example.todo;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper_java extends SQLiteOpenHelper {

    //Database name
    private static final String DATABASE_NAME = "BRAIN_FLAME.db";
    //Table name
    public static final String TABLE_NAME = "todo";
    //Table field
    public static final String COLUMN_PLACE = "question";
    public static final String COLUMN_TIME = "answer";
    public static final String COLUMN_ABOUT = "answer";
    public static final String COLUMN_CAT = "cat";


    public DatabaseHelper_java(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String akili = "create table '" + TABLE_NAME + "' ('" + COLUMN_PLACE + "' TEXT, '" + COLUMN_TIME + "' TEXT, '" + COLUMN_CAT + "' TEXT, '" + COLUMN_ABOUT + "' TEXT)";
        sqLiteDatabase.execSQL(akili);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(sqLiteDatabase);
    }


    @SuppressLint("LongLogTag")
    public boolean read(String p, String t, String a, String c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLACE, p);
        contentValues.put(COLUMN_TIME, t);
        contentValues.put(COLUMN_ABOUT, a);
        contentValues.put(COLUMN_CAT, a);

        Log.d(DATABASE_NAME, "read: Adding" + p + "to" + TABLE_NAME);
        Log.d(DATABASE_NAME, "read: Adding" + t + "to" + TABLE_NAME);
        Log.d(DATABASE_NAME, "read: Adding" + a + "to" + TABLE_NAME);
        Log.d(DATABASE_NAME, "read: Adding" + c + "to" + TABLE_NAME);
        long result = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor use_brain() {
        SQLiteDatabase dbe = this.getReadableDatabase();
        String quary = "SELECT * FROM " + TABLE_NAME;
        Cursor cur = dbe.rawQuery(quary, null);
        return cur;
    }

    public Cursor person_about() {

        SQLiteDatabase dbe = this.getWritableDatabase();
        String quary = ("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CAT + " = PERSONAL");
        Cursor cur = dbe.rawQuery(quary, null);
        return cur;

    }
    public Cursor business_about() {

        SQLiteDatabase dbe = this.getWritableDatabase();
        String quary = ("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CAT + " = PERSONAL ");
        Cursor cur = dbe.rawQuery(quary, null);
        return cur;

    }
}


