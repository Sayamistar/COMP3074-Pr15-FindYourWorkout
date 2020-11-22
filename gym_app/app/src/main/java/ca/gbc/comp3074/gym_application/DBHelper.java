package ca.gbc.comp3074.gym_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("drop table if exists users");
        myDb.execSQL("create table users(username Text primary key, password Text, firstName Text, lastName Text, phone Text, email Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop table if exists users");

    }
    public Boolean insertData(String username, String password,String firstName, String lastName, String phoneNo, String email){
        SQLiteDatabase myDb=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Log.d("TAG", password);
        Log.d("TAG", username);
        cv.put("username",username);
        cv.put("password",password);
        cv.put("firstName",firstName);
        cv.put("lastName",lastName);
        cv.put("phone",phoneNo);
        cv.put("email",email);
        long result=myDb.insert("users",null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }
    public Boolean checkUserName(String username){
        SQLiteDatabase myDb=this.getWritableDatabase();
        Cursor cur=myDb.rawQuery("Select * from users where username=?", new String[]{username});
        if(cur.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
    public Boolean chkUserAndPassword(String username, String password){
        SQLiteDatabase myDb=this.getWritableDatabase();
        Cursor cur=myDb.rawQuery("Select * from users where username=? and password=?", new String[]{username,password});
        if(cur.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
}
