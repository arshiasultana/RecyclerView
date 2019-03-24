package com.example.recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.example.recyclerview.RegistrationContract.*;

public class RegistrationDBHelper extends SQLiteOpenHelper {
//creating a database
    public static final String DATABASE_NAME = "registration.db";
    public static final int DATABASE_VERSION = 1;




    public RegistrationDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_COURSEREGISTRATION_TABLE = "CREATE TABLE " +
                RegistrationEntry.TABLE_NAME + " (" +
                RegistrationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RegistrationEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                RegistrationEntry.COLUMN_COURSE + " TEXT NOT NULL, " +
                RegistrationEntry.COLUMN_YEAR + " INTEGER NOT NULL, " +
                RegistrationEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

         db.execSQL(SQL_CREATE_COURSEREGISTRATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + RegistrationEntry.TABLE_NAME);
        onCreate(db);
    }
}
