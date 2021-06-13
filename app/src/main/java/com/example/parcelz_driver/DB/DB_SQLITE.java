package com.example.parcelz_driver.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_SQLITE extends SQLiteOpenHelper {
    public static final String DBname = "data.db";

    public DB_SQLITE(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table mytable (idf INTEGER PRIMARY KEY , pos VARCHAR)");
        } catch (Exception e) {

        }

        //declare primary value
        ContentValues values = new ContentValues();
        values.put("idf", 57);
        values.put("pos", "605");
        //db.insert("mytable", null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS mytable");
            onCreate(db);
        } catch (Exception e) {

        }


    }


    public boolean insertData(Integer id, String postion) {/////////////////////////////////////////////////////////////
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("idf", id);
        contentValues.put("pos", postion);

        long result = db.insert("mytable", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean updateData(String id, String position) {////////////////////////////////////////////////////
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("pos", position);

        long result = db.update("mytable", contentValues, "idf=?", new String[]{id});
        if (result == -1)
            return false;
        else
            return true;
    }


    public Integer Delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mytable", "id=?", new String[]{id});
    }


    public int getIdForString(String str) {
        int res;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("mytable", new String[] { "pos",
                }, "idf" + "=?",
                new String[] { str }, null, null, null, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            res = cursor.getInt(cursor.getColumnIndex("idf"));
        }
        else {
            res = -1;
        }
        if (cursor != null) {
            cursor.close();
        }
        return res;
    }
    public ArrayList getAllrecord() {//////////////////////////////////////////////////////////////////////
        ArrayList arrayList = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM mytable where idf=88 ", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t2 = res.getString(1);
            arrayList.add(t2);
            res.moveToNext();
        }
        return arrayList;
    }


}


