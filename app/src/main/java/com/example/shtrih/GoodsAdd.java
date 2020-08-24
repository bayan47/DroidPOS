package com.example.shtrih;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class GoodsAdd extends AppCompatActivity {
    Good good_for_add;
    View.OnClickListener goodadd_button_listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_add);

        final Intent goto_goodslist = new Intent(this,GoodsList.class);
        goto_goodslist.putExtra("action_type",(byte)0);

        final EditText goodname_place = findViewById(R.id.GoodName_Place);
        final EditText goodprice_place = findViewById(R.id.GoodPrice_Place);
        Spinner nds_selector = findViewById(R.id.nds_selector);
        Spinner type_selector = findViewById(R.id.type_selector);
        Button goodadd_button = findViewById(R.id.goodaddactivity_button);
        final CheckBox free_price = findViewById(R.id.freePrice_check);


        ArrayAdapter<Kassa.TaxTypes> nds_adapter = new ArrayAdapter<Kassa.TaxTypes>(this,android.R.layout.simple_spinner_item,Kassa.TaxTypes.values());
        nds_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nds_selector.setAdapter(nds_adapter);

        ArrayAdapter<Kassa.PaymentTypes> pay_type_adapter = new ArrayAdapter<Kassa.PaymentTypes>(this,android.R.layout.simple_spinner_item,Kassa.PaymentTypes.values());
        pay_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_selector.setAdapter(pay_type_adapter);

        ArrayAdapter<Kassa.PaymentItemType> pay_item_type_adapter = new ArrayAdapter<Kassa.PaymentItemType>(this,android.R.layout.simple_spinner_item,Kassa.PaymentItemType.values());
        pay_item_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_selector.setAdapter(pay_item_type_adapter);




        AdapterView.OnItemSelectedListener nds_selected = new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                good_for_add.nds = (Kassa.TaxTypes)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener pay_item_type_selected = new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                good_for_add.type = (Kassa.PaymentItemType)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };







        if (getIntent().getExtras().getByte("good_action")==0) //0-Пользователь хочет добавить товар
        {
            good_for_add = new Good();
            goodadd_button_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    good_for_add.name = goodname_place.getText().toString();
                    float prepare_price = Float.parseFloat(goodprice_place.getText().toString());
                    prepare_price = MultiApiHelper.roundFloat(prepare_price,2);
                    prepare_price = prepare_price*100;
                    good_for_add.price = (long) (prepare_price);
                    String sql_text = String.format("INSERT INTO goods (good_name,good_price,good_nds,good_pay_item_type,good_free_price) VALUES ('%s',%d,%d,%d,%d);",good_for_add.name,good_for_add.price,good_for_add.nds.nds_id,good_for_add.type.pay_item_type_id,free_price.isChecked()?1:0);
                    Toast toast = Toast.makeText(getApplicationContext(),"Товар добавлен",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    DBHelper.DataBase.execSQL(sql_text);
                    startActivity(goto_goodslist);
                }
            };
        }
        else if (getIntent().getExtras().getByte("good_action")==1) //1-Пользователь хочет изменить товар
        {
            good_for_add = (Good) getIntent().getSerializableExtra("good_to_change");
            nds_selector.setSelection(good_for_add.nds.nds_id);
            type_selector.setSelection(good_for_add.type.pay_item_type_id-1);
            goodname_place.setText(good_for_add.name);
            goodprice_place.setText(String.valueOf(good_for_add.price/100.0));
            goodadd_button.setText("Обновить товар");
            free_price.setChecked(true);

            Button delete_good_button = new Button(this);
            delete_good_button.setText("Удалить товар");
            delete_good_button.setBackgroundColor(Color.argb(0,0,0,0));
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);
            linearLayout.addView(delete_good_button);


            View.OnClickListener delete_click = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sql_text = String.format("DELETE FROM goods WHERE id=%d;",good_for_add.id);
                    DBHelper.DataBase.execSQL(sql_text);
                    startActivity(goto_goodslist);
                }
            };
            delete_good_button.setOnClickListener(delete_click);






            goodadd_button_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    good_for_add.name = goodname_place.getText().toString();
                    float prepare_price = Float.parseFloat(goodprice_place.getText().toString());
                    prepare_price = MultiApiHelper.roundFloat(prepare_price,2);
                    prepare_price = prepare_price*100;
                    good_for_add.price = (long) (prepare_price);

                    String sql_text = String.format("UPDATE goods SET good_name = '%s',good_price=%d,good_nds=%d,good_pay_item_type=%d,good_free_price=%d WHERE id=%d;",good_for_add.name,good_for_add.price,good_for_add.nds.nds_id,good_for_add.type.pay_item_type_id,free_price.isChecked()?1:0,good_for_add.id);
                    DBHelper.DataBase.execSQL(sql_text);
                    Toast toast = Toast.makeText(getApplicationContext(),"Товар обновлен",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    startActivity(goto_goodslist);
                }
            };
        }

        nds_selector.setOnItemSelectedListener(nds_selected);
        type_selector.setOnItemSelectedListener(pay_item_type_selected);
        goodadd_button.setOnClickListener(goodadd_button_listener);

    }


}