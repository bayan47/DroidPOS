package com.example.shtrih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kassa_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassa_start);

        Button open_button = findViewById(R.id.opensession_button);
        Button close_button = findViewById(R.id.closesession_button);
        Button xprint_button = findViewById(R.id.xprint_button);

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

        open_button.setOnClickListener(open_listener);
        close_button.setOnClickListener(close_listener);
        xprint_button.setOnClickListener(xprint_listener);
    }
}