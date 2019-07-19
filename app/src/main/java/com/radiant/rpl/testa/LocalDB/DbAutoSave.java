package com.radiant.rpl.testa.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbAutoSave extends SQLiteOpenHelper {
    Cursor cursor;
    boolean result = false;
    public static final String DATABASE_NAME = "DbAutoSave";
    public DbAutoSave(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }
    String selectedop;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table autosave (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUID TEXT,QUE TEXT,SELECTEDOPTION TEXT)";
        String query1 = "create table autosave1 (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUID TEXT,QUE TEXT,STATUS TEXT)";
        String query2 = "create table autosave2 (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUID TEXT,QUE TEXT,STATUS TEXT)";
        String query3 = "create table autosave3 (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUID TEXT ,LEFT_TIME TEXT,SYSTEM_TIME TEXT)";
        String query4 = "create table autosave4 (ID INTEGER PRIMARY KEY AUTOINCREMENT,IDD TEXT,STUID TEXT)";
        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists autosave");
        db.execSQL("drop table if exists autosave1");
        db.execSQL("drop table if exists autosave2");
        db.execSQL("drop table if exists autosave3");
        db.execSQL("drop table if exists autosave4");
        onCreate(db);
    }



    //Insert pallete status of the questions
    public void insertData(String stuid, String queid,String queno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",queid );
        cv.put("QUE", stuid);
        cv.put("SELECTEDOPTION",queno);
        db.insert("autosave", null, cv);
    }

    //Update pallete status of the questions
    public void insertDataunanswered(String stuid,String queid, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",stuid );
        cv.put("QUE",queid);
        cv.put("STATUS", status);
        db.insert("autosave1", null, cv);
    }

    public void updateDataunanswered(String stuid, String queid,String statu,String queiddd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",stuid );
        cv.put("QUE", queid);
        cv.put("STATUS",statu);
        db.update("autosave1",cv, "QUE = ?",new String[]{queiddd});
    }

    public void insertDataunanswered1(String stuid,String queid, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",stuid );
        cv.put("QUE",queid);
        cv.put("STATUS", status);
        db.insert("autosave2", null, cv);
    }

    public void updateDataunanswered1(String stuid, String queid,String statu,String queiddd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",stuid );
        cv.put("QUE", queid);
        cv.put("STATUS",statu);
        db.update("autosave2",cv, "QUE = ?",new String[]{queiddd});
    }

    public String getDataOfSingleClientstatus1(String Que){
        String selectQuery = "SELECT  * FROM " + "autosave2" + " WHERE " + "QUE" + "='" + Que +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        try{
        if (cursor.getCount()>0){
            cursor.moveToNext();
            selectedop=cursor.getString(2);
            return selectedop;
        }
        else {
            return  null;
        }}finally {
                // this gets called even if there is an exception somewhere above
                if(cursor != null)
                    cursor.close();
            }

    }

    public Cursor getData11(String queryData) {
        String[] selection = {queryData};
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from autosave2 where STUID =?"+"ORDER BY"+" ID ASC";
        cursor = db.rawQuery(query, selection);
        if (cursor.getCount() != 0) {
        }
        return cursor;
    }


    public Cursor getData(String queryData) {
        String[] selection = {queryData};
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from autosave where STUID =?"+"ORDER BY"+" QUE ASC";
        cursor = db.rawQuery(query, selection);
        if (cursor.getCount() != 0) {
        }
        return cursor;
    }

    public Cursor getData1(String queryData) {
        String[] selection = {queryData};
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from autosave1 where STUID =?"+"ORDER BY"+" ID ASC";
        cursor = db.rawQuery(query, selection);
        if (cursor.getCount() != 0) {
        }
        return cursor;
    }

    //Get status of the single question
    public String getDataOfSingleClientstatus(String Que){
        String selectQuery = "SELECT  * FROM " + "autosave1" + " WHERE " + "QUE" + "='" + Que +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        try{
        if (cursor.getCount()>0){
            cursor.moveToNext();
            selectedop=cursor.getString(2);
            return selectedop;
        }
        else {
            return  null;
        }}finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }

    public String getStatusDataOfSingleClientstatus(String Que){
        String selectQuery = "SELECT  * FROM " + "autosave1" + " WHERE " + "QUE" + "='" + Que +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        try{
            if (cursor.getCount()>0){
                cursor.moveToNext();
                selectedop=cursor.getString(3);
                return selectedop;
            }
            else {
                return  null;
            }}finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }


    public String getStatusDataOfSingleClientstatus1(String Que){
        String selectQuery = "SELECT  * FROM " + "autosave2" + " WHERE " + "QUE" + "='" + Que +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        try{
            if (cursor.getCount()>0){
                cursor.moveToNext();
                selectedop=cursor.getString(3);
                return selectedop;
            }
            else {
                return  null;
            }}finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }

    public String getDatasinglestatus(String queryData1) {
        String[] selection = {queryData1};
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select STATUS from autosave1 where QUE =?";
        cursor = db.rawQuery(query, selection);
        if (cursor.getCount() != 0) {
        }
        return query;
    }


    public String getDataOfSingleClient(String Que){
        String selectQuery = "SELECT  * FROM " + "autosave" + " WHERE " + "QUE" + "='" + Que +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        try {
        if (cursor.getCount()>0){
            cursor.moveToNext();
            selectedop=cursor.getString(2);
            return selectedop;
        }
        else {
            return  null;
        }
        }finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }

    public String getD(String q){
        String selectq="SELECT  * FROM " + "autosave" + " WHERE " + "QUE" + "='" + q +  "'";


        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectq,null);
        try{
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            String aa=cursor.getString(3);
            return aa;

        }
        else {
            return  null;
        }}finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }

    public String getusername(String q){
        String selectq="SELECT  * FROM " + "autosave" + " WHERE " + "QUE" + "='" + q +  "'";


        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectq,null);
        try{
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                String aa=cursor.getString(1);
                return aa;

            }
            else {
                return  null;
            }}finally {
            // this gets called even if there is an exception somewhere above
            if(cursor != null)
                cursor.close();
        }

    }

    public void updateData(String stuid, String queid,String queno,String queiddd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID",queid );
        cv.put("QUE", stuid);
        cv.put("SELECTEDOPTION",queno);
        db.update("autosave",cv, "QUE = ?",new String[]{queiddd});
    }



    //Questions list

    public void insertStudentid(String stuid,String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID", stuid);
        cv.put("IDD",id);
        db.insert("autosave4", null, cv);
    }

    public void updateStudentid(String stuid, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUID", stuid);
        db.update("autosave4",cv, "IDD = ?",new String[]{id});
    }

    public String getstudentid(String id){
        String selectQuery = "SELECT  * FROM " + "autosave4" + " WHERE " + "IDD" + "='" + id +  "'";

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if (cursor.getCount()>0){
            cursor.moveToNext();
            selectedop=cursor.getString(2);
            return selectedop;
        }
        else {
            return  null;
        }

    }

    public void onDelete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists autosave");
        db.execSQL("drop table if exists autosave1");
        db.execSQL("drop table if exists autosave2");
        db.execSQL("drop table if exists autosave3");
        db.execSQL("drop table if exists autosave4");
        onCreate(db);
    }
}
