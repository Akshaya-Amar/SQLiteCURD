package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteapp.UserContract.UserEntry;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public UserDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery = "CREATE TABLE " +
                UserEntry.TABLE_NAME + "(" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_AGE + " INTEGER NOT NULL, " +
                UserEntry.COLUMN_NUMBER + " LONG NOT NULL" +
                ");";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(db);
    }

    public long addUserRecord(String name, int age, long number) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME, name);
        values.put(UserEntry.COLUMN_AGE, age);
        values.put(UserEntry.COLUMN_NUMBER, number);

        return database.insert(
                UserEntry.TABLE_NAME,
                null,
                values
        );
    }

    public int deleteUserRecord(int userId) {

        SQLiteDatabase database = getWritableDatabase();

        return database.delete(
                UserEntry.TABLE_NAME,
                UserEntry._ID + "=?",
                new String[]{String.valueOf(userId)}
        );
    }

    public int updateUserRecord(int userId, String name, int age, long number) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME, name);
        values.put(UserEntry.COLUMN_AGE, age);
        values.put(UserEntry.COLUMN_NUMBER, number);

        return database.update(
                UserEntry.TABLE_NAME,
                values,
                UserEntry._ID + "=?",
                new String[]{String.valueOf(userId)}
        );
    }

    public Cursor getAllRecords() {

        SQLiteDatabase database = getReadableDatabase();

        return database.query(
                UserEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
