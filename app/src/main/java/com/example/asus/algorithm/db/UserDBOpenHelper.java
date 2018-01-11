package com.example.asus.algorithm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 2018/1/10.
 */

public class UserDBOpenHelper extends SQLiteOpenHelper {
    public UserDBOpenHelper(Context context) {
        super(context,"user.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL("CREATE table user(\n" +
                "  id  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  phone varchar(11),\n" +
                "  password varchar(8)\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
