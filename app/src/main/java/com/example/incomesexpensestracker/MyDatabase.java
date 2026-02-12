package com.example.incomesexpensestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION=1;
    //Database verification
    private static final String DATABASE_NAME="mydatabase";

    public MyDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create notes table
        String createQuery = "Create table mytable(id Integer primary key autoincrement, date text,income text,expenses text)";
        db.execSQL(createQuery);
    }
    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("Drop table if EXISTS "+DATABASE_NAME);
        //Create tables again
        onCreate(db);
    }
    //code to insert data
    public void insertData(String date,String income,String expenses){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",date);
        contentValues.put("income",income);
        contentValues.put("expenses",expenses);

        //inserting row
        db.insert("mytable",null,contentValues);
        db.close();
    }
    //code to select data
    public Cursor selectData(String date){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from mytable where date=?";
        Cursor cursor=db.rawQuery(query,new String[]{date});
        return cursor;
    }
    public Cursor selectData(String date1,String date2){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from mytable where date between ? and ?";
        Cursor cursor=db.rawQuery(query,new String[]{date1,date2});
        return cursor;
    }
    //code to update data
    public void updateData(String id,String date,String income,String expenses){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",date);
        contentValues.put("income",income);
        contentValues.put("expenses",expenses);
        //updating row
        db.update("mytable",contentValues,"id=?",new String[]{id});
        db.close();
    }
    //code to delete data
    public void deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        //deleting row
        db.delete("mytable","id=?",new String[]{id});
        db.close();
    }
}
