package com.example.shtrih;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GoodsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        final Intent goto_goodsadd = new Intent(this,GoodsAdd.class);

        ImageButton goodaddactivity_button = findViewById(R.id.goodaddactivity_button);


        DBHelper.DataBase.execSQL("CREATE TABLE IF NOT EXISTS goods (id INTEGER PRIMARY KEY, good_name TEXT, good_price INTEGER, good_nds INTEGER, good_pay_item_type INTEGER)");
        List<Good> goods = new ArrayList<Good>();
        Cursor query = DBHelper.DataBase.rawQuery("SELECT * FROM goods;", null);
        if (query.moveToFirst()) {
            do {
                Good new_good = new Good(query.getInt(0), query.getString(1), query.getLong(2), Good.getNDS(query.getInt(3)), Good.getpaytype(query.getInt(4)));
                goods.add(new_good);
            }
            while (query.moveToNext());
        }

        ListView goods_list = findViewById(R.id.ListView);

        View.OnClickListener good_add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(goto_goodsadd);
            }
        };
        goodaddactivity_button.setOnClickListener(good_add_listener);


        goods_list.setAdapter(new GoodsListAdapted(this,goods));
        Toast.makeText(this,String.valueOf(goods.size()),Toast.LENGTH_LONG).show();

    }


}