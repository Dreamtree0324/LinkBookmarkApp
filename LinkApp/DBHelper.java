package com.example.administrator.dnf;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "linkdb", null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table tb_link (_id integer primary key autoincrement, title text, link text)";
        db.execSQL(memoSQL);

        String title, link;
        for(int i=1; i<=20; i++){
            title = i + "번째 즐겨찾기";
            link = "링크";
            db.execSQL("insert into tb_link (title, link) values (?,?)", new String[]{title, link});
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tb_link");
            onCreate(db);
        }
    }
}
