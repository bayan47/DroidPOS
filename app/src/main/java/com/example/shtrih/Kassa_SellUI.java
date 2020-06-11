package com.example.shtrih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Kassa_SellUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassa__sell_u_i);

        EditText good_quantity = findViewById(R.id.GoodQuantity);
        EditText good_price = findViewById(R.id.GoodPrice);

    }
}