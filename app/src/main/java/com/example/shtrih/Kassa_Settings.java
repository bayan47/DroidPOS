package com.example.shtrih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kassa_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassa_start);

        final Intent goto_check = new Intent(this,Kassa_SellUI.class);

        Button open_button = findViewById(R.id.opensession_button);
        Button close_button = findViewById(R.id.closesession_button);
        Button xprint_button = findViewById(R.id.xprint_button);
        Button check_button = findViewById(R.id.check_button);

        View.OnClickListener open_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kassa.OpenSession();
            }
        };
        View.OnClickListener close_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kassa.CloseSession();
            }
        };
        View.OnClickListener xprint_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kassa.XPrint();
            }
        };
        View.OnClickListener check_button_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(goto_check);
            }
        };

        open_button.setOnClickListener(open_listener);
        close_button.setOnClickListener(close_listener);
        xprint_button.setOnClickListener(xprint_listener);
        check_button.setOnClickListener(check_button_listener);
    }
}