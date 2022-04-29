package com.parisubalan.sqlDatabase.database;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.parisubalan.sqlDatabase.activity.MainActivity;
import com.parisubalan.sqlDatabase.pojo.PojoClass;

import java.util.ArrayList;


public class SqliteDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Student_Details";
    private static final String TABLE_STUDENTS = "Students_Details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PH_NO = "Mobile";
    private static final String KEY_STANDARD = "Standred";
    private static final String KEY_SECTION = "Section";

    SQLiteDatabase db;
    ContentValues values;

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Student_Table = " CREATE TABLE " + TABLE_STUDENTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT," + KEY_STANDARD + " TEXT," + KEY_SECTION + " TEXT" + ")";
        db.execSQL(Student_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void addDetail(PojoClass pojo_class) {
        db = this.getWritableDatabase();
        values = new ContentValues();

        values.put(KEY_NAME, pojo_class.getName());
        values.put(KEY_PH_NO, pojo_class.getMobile());
        values.put(KEY_STANDARD, pojo_class.getStandard());
        values.put(KEY_SECTION, pojo_class.getSection());

        db.insert(TABLE_STUDENTS, null, values);
        Log.e("TAG", "Detail: Added");
    }

    public void updateDetail(PojoClass pojo_class) {
        db = this.getWritableDatabase();
        values = new ContentValues();

        values.put(KEY_NAME, pojo_class.getName());
        values.put(KEY_PH_NO, pojo_class.getMobile());
        values.put(KEY_STANDARD, pojo_class.getStandard());
        values.put(KEY_SECTION, pojo_class.getSection());

        String[] whereArgs = {pojo_class.getStandard()};
        db.update(TABLE_STUDENTS, values, KEY_STANDARD + "=?", whereArgs);
        Log.e("TAG", "Detail: Updated");

    }

    public void deleteDetail(PojoClass pojo_class) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {pojo_class.getName()};
        db.delete(TABLE_STUDENTS, KEY_NAME + "=?", whereArgs);
        Log.e("TAG", "Detail: Deleted");

    }

    public ArrayList<PojoClass> readData(String name) {
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS +" WHERE "+ KEY_NAME+ " like" +" '%"+name+"%'",null);
        ArrayList<PojoClass> dataArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                dataArrayList.add(new PojoClass(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());

            cursor.close();
        }
        return dataArrayList;
    }
}
