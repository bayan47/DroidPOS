package com.example.shtrih;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cashier_RMK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier__r_m_k);


        final ArrayList<Good> list_for_sell = (ArrayList<Good>) getIntent().getSerializableExtra("goods_to_sell_list");


        final Intent goto_free_sell = new Intent(this,Kassa_SellUI.class);
        final Intent goto_goods_list = new Intent(this,GoodsList.class);
        goto_goods_list.putExtra("action type",(byte)1);

        final GoodsListAdapted adapter;

        final ListView sellgoods_storage = (ListView) findViewById(R.id.selling_goods_list);
        Button sell_button = (Button) findViewById(R.id.sell_button);

        Button good_add_sell_list = (Button) findViewById(R.id.good_add_sell_list_button);
        Button free_sell_button = (Button) findViewById(R.id.good_free_price_sell);

        if (list_for_sell!=null)
            {
            adapter = new GoodsListAdapted(this, list_for_sell, R.layout.goods_list_layout);
            sellgoods_storage.setAdapter(adapter);
            }

        View.OnClickListener good_add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goto_goods_list);
            }
        };

        View.OnClickListener free_sell_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goto_free_sell);
            }
        };

        View.OnClickListener sell_button_lister = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( list_for_sell != null) {
                    Kassa.CashOperation2(list_for_sell);
                    list_for_sell.clear();
                    sellgoods_storage.invalidateViews();
                    Toast toast =  Toast.makeText(getApplicationContext(),"Операция выполнена",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else
                {
                    Toast toast =  Toast.makeText(getApplicationContext(),"Корзина пуста",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        };

        good_add_sell_list.setOnClickListener(good_add_listener);
        free_sell_button.setOnClickListener(free_sell_listener);
        sell_button.setOnClickListener(sell_button_lister);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id){
            case R.id.menu_settings_button :
                Intent goto_settings = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goto_settings);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}