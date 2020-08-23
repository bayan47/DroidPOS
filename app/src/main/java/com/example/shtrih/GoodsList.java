package com.example.shtrih;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GoodsList extends AppCompatActivity {


    ListView goods_list;
    List<Good> goods;
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

                    goto_cashier.putExtra("goods_to_sell_list", Kassa.goods_to_sell_list);
                    startActivity(goto_cashier);
                }
            };
            goodaddactivity_button.setOnClickListener(good_add_listener);
        }


        DBHelper.DataBase.execSQL("CREATE TABLE IF NOT EXISTS goods (id INTEGER PRIMARY KEY, good_name TEXT, good_price INTEGER, good_nds INTEGER, good_pay_item_type INTEGER, good_free_price INTEGER)");
        goods = new ArrayList<Good>();
        Cursor query = DBHelper.DataBase.rawQuery("SELECT * FROM goods;", null);
        if (query.moveToFirst()) {
            do {
                Good new_good = new Good(query.getInt(0), query.getString(1), query.getLong(2), Good.getNDS(query.getInt(3)), Good.getpaytype(query.getInt(4)),query.getInt(5));
                goods.add(new_good);
            }
            while (query.moveToNext());
        }

         goods_list = findViewById(R.id.ListView);


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


                    showDialog(findViewById(R.id.goodaddactivity_button).getRootView(),test_good.name, test_good.price,test_good);



                }
            };
        }
        return listener;
    }

    public void showDialog(View v, String good_name, long price,Good good) {

        Good_Add_Dialog dialog = new Good_Add_Dialog();
        dialog.header_text = good_name;
        dialog.price = price;
        dialog.good = good;
        dialog.adapted = (GoodsListAdapted)goods_list.getAdapter();
        dialog.goods_to_check = Kassa.goods_to_sell_list;
        dialog.show(getSupportFragmentManager(), "custom");

    }


}

