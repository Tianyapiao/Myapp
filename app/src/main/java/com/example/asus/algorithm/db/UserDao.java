package com.example.asus.algorithm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.algorithm.db.entity.User;

/**
 * Created by Asus on 2018/1/10.
 * 封装增删改查
 */

public class UserDao {
    //维持一个UserDBOpenHelper的引用变量
    private UserDBOpenHelper mHelper;
    private SQLiteDatabase db;
    private ContentValues values;

    public UserDao(Context context){
      mHelper=new UserDBOpenHelper(context);
      db= mHelper.getWritableDatabase();
    }

    //添加
    private boolean add( String phone, String password){

        //存储数据，类似于HashMap
        values = new ContentValues();
        values.put("phone",phone);
        values.put("password",password);
        long row = db.insert("user", null, values);
        db.close();//关闭数据库
        return row!=-1;
    }

    public boolean insert(User user) {
        return add(user.getPhone(),user.getPassword());
    }

    //删除
    public boolean delete(String id){
        int count =db.delete("user","id=?",new String[]{id});
        db.close();
        return count > 0;
    }

    public boolean register(String phone){
        Cursor cursor = db.rawQuery("select * from user where phone=" + phone, null);
        if (cursor!=null){
            return false;//数据库中有数据注册失败
        }else{
            cursor.close();
            db.close();
            return true;
        }
    }

}
