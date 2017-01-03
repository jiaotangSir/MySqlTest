package com.jiaotang.mysqltest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

public class Main2Activity extends AppCompatActivity {

    private MainActivity.MySqlHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void insertDataTwo(View view) {
        dbHelper = new MainActivity.MySqlHelper(this,"BookStore.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //组装数据
        values.put("myDate",df.format(new java.util.Date()));
        values.put("lat",39.000);
        values.put("id",30);
        //执行插入数据
        db.insert("Book",null,values);
    }
}
