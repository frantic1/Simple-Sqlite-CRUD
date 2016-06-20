package com.frantic.simplecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frantic on 6/15/2016.
 */
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Sagarmatha";
    private static final int DATABASE_Version=1;
    private static final String TABLE_STUDENT = "students";

    // Students Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_FACULTY = "faculty";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";

    private static final String CREATE_TABLE="CREATE TABLE " + TABLE_STUDENT + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT,"+ KEY_FACULTY + " TEXT,"+
            KEY_PHONE + " TEXT,"
            + KEY_EMAIL + " TEXT" + ")";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Table","Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_ADDRESS,student.getAddress());
        values.put(KEY_FACULTY,student.getFaculty());
        values.put(KEY_PHONE,student.getPhone());
        values.put(KEY_EMAIL,student.getEmail());

        db.insert(TABLE_STUDENT,null,values);
        Log.d("Student","Added");
        db.close();
    }

    public int getCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = (int) DatabaseUtils.queryNumEntries(db,TABLE_STUDENT);
        return rows;
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PRODUCTS,null);
        Cursor cursor = db.query(TABLE_STUDENT,null,null,null,null,null,null);
        //cursor.moveToFirst();
        Student student;
        if (cursor.getCount()>0){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setAddress(cursor.getString(2));
                student.setFaculty(cursor.getString(3));
                student.setPhone(cursor.getString(4));
                student.setEmail(cursor.getString(5));
                studentList.add(student);
                //cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public void deleteRecord(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT,KEY_ID+"= ?",new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public void updateRecord(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_ADDRESS,student.getAddress());
        values.put(KEY_FACULTY,student.getFaculty());
        values.put(KEY_PHONE,student.getPhone());
        values.put(KEY_EMAIL,student.getEmail());
        db.update(TABLE_STUDENT, values, KEY_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }
}
