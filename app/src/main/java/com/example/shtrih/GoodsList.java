package com.example.shtrih;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GoodsList extends AppCompatActivity {

    ArrayList<Good> goods_to_sell_list = new ArrayList<Good>();

    Intent goto_goodsadd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        byte action_type = getIntent().getExtras().getByte("action type"); //0-Добавление нового товара, 1-Добавление товара в лист продажи

        goto_goodsadd= new Intent(this, GoodsAdd.class);
        goto_goodsadd.putExtra("good_action",(byte)0);

        final Intent goto_cashier = new Intent(this,Cashier_RMK.class);


        ImageButton goodaddactivity_button = findViewById(R.id.goodaddactivity_button);



        if (action_type == 0) {
            View.OnClickListener good_add_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goto_goodsadd.putExtra("good_action",(byte)0);
                    startActivity(goto_goodsadd);
                }
            };
            goodaddactivity_button.setOnClickListener(good_add_listener);
        } else if (action_type == 1) {
            goodaddactivity_button.setImageResource(R.drawable.galo4ka);
            View.OnClickListener good_add_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    goto_cashier.putExtra("goods_to_sell_list", goods_to_sell_list);
                    startActivity(goto_cashier);
                }
            };
            goodaddactivity_button.setOnClickListener(good_add_listener);
        }


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


        goods_list.setOnItemClickListener(setClickListener(action_type));


        FillGoodsList(this, goods, goods_list, R.layout.goods_list_layout);


    }


    public static void FillGoodsList(Context context, List<Good> goods_list, ListView listView, int layout) {
        listView.setAdapter(new GoodsListAdapted(context, goods_list, layout));
    }

    public AdapterView.OnItemClickListener setClickListener(byte action_type) {
        AdapterView.OnItemClickListener listener = null;

        if (action_type == 0) {
            listener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Good test_good = (Good) adapterView.getItemAtPosition(i);
                    goto_goodsadd.putExtra("good_to_change",test_good);
                    goto_goodsadd.putExtra("good_action",(byte)1);
                    startActivity(goto_goodsadd);
                }
            };
        }

        if (action_type == 1) {
            listener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Good test_good = (Good) adapterView.getItemAtPosition(i);
                    goods_to_sell_list.add(test_good);

                }
            };
        }
        return listener;
    }


}

