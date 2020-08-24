package com.example.shtrih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cashier_RMK extends AppCompatActivity {

    Button sell_button;
    float final_sum;
    ArrayList<Good> list_for_sell=null;
    Cashier_RMK chto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier__r_m_k);


        list_for_sell = Kassa.goods_to_sell_list;

        final_sum = 0;
        DecimalFormat df = new DecimalFormat("###.##");
        for (Good good:list_for_sell)
        {
            float f = (good.count*good.price)/100;
            final_sum = final_sum + MultiApiHelper.roundFloat(f,2);

        }



        final Intent goto_free_sell = new Intent(this,Kassa_SellUI.class);
        final Intent goto_goods_list = new Intent(this,GoodsList.class);
        goto_goods_list.putExtra("action type",(byte)1);

        final GoodsListAdapted adapter;

        final ListView sellgoods_storage = (ListView) findViewById(R.id.selling_goods_list);
        sell_button = (Button) findViewById(R.id.sell_button);


        sell_button.setText(list_for_sell.size()>0?"К оплате: "+ String.valueOf(final_sum)+ " Р":"К оплате: 0,00 Р");

        Button good_add_sell_list = (Button) findViewById(R.id.good_add_sell_list_button);
        Button free_sell_button = (Button) findViewById(R.id.good_free_price_sell);

        if (list_for_sell!=null)
            {
            adapter = new GoodsListAdapted(this, list_for_sell, R.layout.goods_list_layout);
            sellgoods_storage.setAdapter(adapter);
            }

        final View.OnClickListener good_add_listener = new View.OnClickListener() {
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
                    //Toast toast =  Toast.makeText(getApplicationContext(),Kassa.device.Get_ResultCodeDescription(),Toast.LENGTH_SHORT);
                    //Toast toast =  Toast.makeText(getApplicationContext(),Kassa.device.Get_Price()+"|"+Kassa.device.Get_Quantity()+"|"+Kassa.device.Get_Summ1() ,Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER,0,0);
                    //toast.show();
                    UpdateText();
                    final_sum=0;
                }
                else
                {
                    Toast toast =  Toast.makeText(getApplicationContext(),"Корзина пуста",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        };

        sellgoods_storage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Good_Remove_Dialog good_remove_dialog = new Good_Remove_Dialog();
                good_remove_dialog.good = (Good) adapterView.getItemAtPosition(i);
                good_remove_dialog.adapted = (GoodsListAdapted) sellgoods_storage.getAdapter();
                good_remove_dialog.list_good = list_for_sell;
                good_remove_dialog.activity = Cashier_RMK.this;
                good_remove_dialog.show(getSupportFragmentManager(),"good_remove");
                return true;
            }
        });


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

    public void UpdateText()
    {
        sell_button.setText(list_for_sell.size()>0?"К оплате: "+ String.valueOf(final_sum)+ " Р":"К оплате: 0,00 Р");
    }



}