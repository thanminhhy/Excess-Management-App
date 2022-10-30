package com.expensemanagementapp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "TripManagement.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Trip";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "trip_name";
    private static final String COLUMN_DEPARTURE = "trip_departure";
    private static final String COLUMN_DESTINATION = "trip_destination";
    private static final String COLUMN_DATE = "trip_date";
    private static final String COLUMN_FullName = "trip_customerName";
    private static final String COLUMN_RISKSASSESSMENT = "trip_risksAssessnebt";
    private static final String COLUMN_DESCRIPTION = "trip_description";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DEPARTURE + " TEXT, " +
                        COLUMN_DESTINATION + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_FullName + " TEXT, " +
                        COLUMN_RISKSASSESSMENT + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTrip(String name,String departure, String destination, String date,String customerName, String risksAssessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(name.length() ==0 || departure.length()==0 ||
                destination.length()==0 || date.length()==0 || customerName.length()==0 ||
                risksAssessment.length()==0 || description.length()==0){
            Toast.makeText(context,"Please fill up all the information", Toast.LENGTH_SHORT).show();
        }
        else{
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DEPARTURE, departure);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_FullName, customerName);
        cv.put(COLUMN_RISKSASSESSMENT, risksAssessment);
        cv.put(COLUMN_DESCRIPTION, description);}

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Add Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name,String departure, String destination, String date,String customerName, String risksAssessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DEPARTURE, departure);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_FullName, customerName);
        cv.put(COLUMN_RISKSASSESSMENT, risksAssessment);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME,cv,"_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context,"Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Update Successfully", Toast.LENGTH_SHORT).show();
        }
     }

}
