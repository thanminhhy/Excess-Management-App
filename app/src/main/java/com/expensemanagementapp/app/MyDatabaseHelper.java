package com.expensemanagementapp.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "TripManagement.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Trip";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "trip_name";
    private static final String COLUMN_DESTINATION = "trip_destination";
    private static final String COLUMN_DATE = "trip_date";
    private static final String COLUMN_RISKSASSESSMENT = "trip_riskassessnebt";
    private static final String COLUMN_DESCRIPTION = "trip_description";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESTINATION + " TEXT, " +
                        COLUMN_DATE + " DATE, " +
                        COLUMN_RISKSASSESSMENT + " BOOLEAN, " +
                        COLUMN_DESTINATION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
