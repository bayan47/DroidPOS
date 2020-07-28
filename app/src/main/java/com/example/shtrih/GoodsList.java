package com.example.shtrih;

import android.support.v7.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class GoodsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        SQLiteDatabase goods_db = getBaseContext().openOrCreateDatabase("ps34.db", MODE_PRIVATE, null);
        goods_db.execSQL("CREATE TABLE IF NOT EXISTS goods (id INTEGER PRIMARY KEY, good_name TEXT, good_price INTEGER, good_nds INTEGER,good_paymenttype INTEGER, good_pay_item_type INTEGER)");

    }
}