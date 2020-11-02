package com.example.myapplicationpp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class CreateNewsDB extends SQLiteOpenHelper{

    public static final String CREATE_NEWS="create table NewsTable(" +
            "nlID integer primary key autoincrement," +
            "Title text," +
            "Content text" +
            ",Source text," +
            "time text," +
            "imageSource int)";

    private Context mContex;
    public CreateNewsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //若相同名字的数据库已经存在则先删除
        if(getDatabaseName()==name)
        {
            context.deleteDatabase(name);
        }
        mContex=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
        Toast.makeText(mContex,"create succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists NewsTable");
        onCreate(db);
    }
}