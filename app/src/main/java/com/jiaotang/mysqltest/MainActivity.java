package com.jiaotang.mysqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private MySqlHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MySqlHelper(this,"BookStore.db",null,1);

    }

    //按钮点击事件
    public void sqliteClick(View view) {
        dbHelper.getWritableDatabase();
    }

    /**插入数据*/
    public void insertData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //组装数据
            values.put("myDate",df.format(new java.util.Date()));
            values.put("lat",116.0205);
            values.put("id",20);
            //执行插入数据
            db.insert("Book",null,values);


//        for (int i=0; i<10; i++) {
//            //组装数据
//            values.put("myDate",df.format(new java.util.Date()));
//            values.put("lat",116.0205+i);
//            values.put("id",i+10);
//            //执行插入数据
//            db.insert("Book",null,values);
//            values.clear();
//        }


    }

    /**查询数据*/
    public void queryData(View view) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.query("Book",null,null,null,null,null,"ORDER BY id DESC");
        Cursor cursor = db.rawQuery("select * from Book ORDER BY myDate DESC", null);
//        Cursor cursor = db.rawQuery("select * from Book where lat <= ? and lat >=?", new String[]{"123.00","119.00"});

        if (cursor.moveToFirst()) {
            do {
                String myDate = cursor.getString(cursor.getColumnIndex("myDate"));
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Log.d("data","日期："+myDate);
                Log.d("data","经纬度："+String.valueOf(lat));
                Log.d("data", "数据ID："+id);

            } while (cursor.moveToNext());
        }
        cursor.close();

    }


    public static class  MySqlHelper extends SQLiteOpenHelper{

        public static final String CREATE_TABLE = "create table Book("
                +"id integer primary key,"
                +"myDate text,"
                +"lat real)";

        public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Log.d("data","成功创建Book表");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
