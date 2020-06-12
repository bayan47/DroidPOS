package com.example.shtrih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Kassa_SellUI extends AppCompatActivity {

  // Kassa.TaxTypes[] taxTypes = {Kassa.TaxTypes.WithoutNDS,Kassa.TaxTypes.NDS20, Kassa.TaxTypes.NDS10, Kassa.TaxTypes.NDS20120, Kassa.TaxTypes.NDS10110};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kassa__sell_u_i);

        final EditText good_name = findViewById(R.id.GoodName);
        final EditText good_quantity = findViewById(R.id.GoodQuantity);
        final EditText good_price = findViewById(R.id.GoodPrice);

        Button sell_button = findViewById(R.id.sell_button);

        Spinner nds_selector = (Spinner)findViewById(R.id.nds);
        Spinner pay_type_selector = (Spinner)findViewById(R.id.payment_type);
        Spinner pay_item_type_selector = (Spinner)findViewById(R.id.pay_item_type);



        View.OnClickListener sell_button_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kassa.goodName = good_name.getText().toString();
                Kassa.quantity = Double.parseDouble(good_quantity.getText().toString());
                Kassa.price = Kassa.ParsePrice(good_price.getText().toString());
                Kassa.Sell();
            }
        };
        sell_button.setOnClickListener(sell_button_listener);

        ArrayAdapter<Kassa.TaxTypes> nds_adapter = new ArrayAdapter<Kassa.TaxTypes>(this,android.R.layout.simple_spinner_item,Kassa.TaxTypes.values());
        nds_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nds_selector.setAdapter(nds_adapter);

        ArrayAdapter<Kassa.PaymentTypes> pay_type_adapter = new ArrayAdapter<Kassa.PaymentTypes>(this,android.R.layout.simple_spinner_item,Kassa.PaymentTypes.values());
        pay_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pay_type_selector.setAdapter(pay_type_adapter);

        ArrayAdapter<Kassa.PaymentItemType> pay_item_type_adapter = new ArrayAdapter<Kassa.PaymentItemType>(this,android.R.layout.simple_spinner_item,Kassa.PaymentItemType.values());
        pay_item_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pay_item_type_selector.setAdapter(pay_item_type_adapter);

        AdapterView.OnItemSelectedListener nds_selected = new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                Kassa.taxType = (Kassa.TaxTypes)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener pay_type_selected = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Kassa.paymentType = (Kassa.PaymentTypes)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        AdapterView.OnItemSelectedListener pay_item_type_selected = new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                Kassa.paymentItemType = (Kassa.PaymentItemType)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        nds_selector.setOnItemSelectedListener(nds_selected);
        pay_type_selector.setOnItemSelectedListener(pay_type_selected);
        pay_item_type_selector.setOnItemSelectedListener(pay_item_type_selected);



    }
}