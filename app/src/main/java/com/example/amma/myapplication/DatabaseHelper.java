package com.example.amma.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "Student.db";
    public static final  String TABLE_NAME = "student_table";
    public static final  String COL_1 = "ID";
    public static final  String COL_2 = "NAME";
    public static final  String COL_3 = "SURNAME";
    public static final  String COL_4 = "MARKS";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " +TABLE_NAME
                +" ("
                +COL_1 +" integer PRIMARY KEY autoincrement,"
                +COL_2 +" text,"
                +COL_3 +" text,"
                +COL_4 +" text"
                +");";
        //INTEGER DATATYPE USED WHEN VALUEOF() IS USED
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String name,String surname,String marks){
     SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,name);
        values.put(COL_3,surname);
        values.put(COL_4,marks);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getallData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
        return result;
    }
    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME,contentValues,COL_1+" = ?",new String[] {id});
        return  true;


    }
    public Integer delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete from " +TABLE_NAME +" where "+COL_1 +"=");
        return db.delete(TABLE_NAME,COL_1+" = ?",new String[] {id});
    }

}
