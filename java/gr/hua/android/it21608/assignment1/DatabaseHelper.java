package gr.hua.android.it21608.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    //DB name,table name,and column names
    public static String DATABASE_NAME = "HuaStudentDB";
    private static final int VERSION = 1;
    public static String TABLE_NAME = "hua_student_table";
    public static String COLUMN_1 = "ID";
    public static String COLUMN_2 = "fname";
    public static String COLUMN_3 = "lname";
    public static String COLUMN_4 = "AGE";

    //DB constructor,when it's called DB is created
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,lname TEXT,AGE INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String fname, String lname, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, fname);
        contentValues.put(COLUMN_3, lname);
        contentValues.put(COLUMN_4, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //if its not inserted insert method returns -1
        if (result == -1)
            return false;
        else
            return true;
    }

    //select * from DB
    //    public Cursor getAllData() {
    //        SQLiteDatabase db = this.getWritableDatabase();
    //        Cursor results = db.rawQuery("select * from " + TABLE_NAME, null);
    //        return results;
    //    }

    //query with fname
    public Cursor fnameQuery(String searchField) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_2 + " LIKE '%" + searchField + "%'", null);
        //Cursor results = db.rawQuery("select * from "+TABLE_NAME+" where "+COLUMN_2+" like '%"+searchField+"%'",null);
        //Cursor results = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_2 + " like 'ba'", null);
        return results;


    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
