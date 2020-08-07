package com.example.shtrih;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Cashier_Logon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_cashier__logon);


        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        final EditText pass_text = (EditText) findViewById(R.id.cashier_pass);
        Button num_button1 = (Button) findViewById(R.id.num_1);
        Button num_button2 = (Button) findViewById(R.id.num_2);
        Button num_button3 = (Button) findViewById(R.id.num_3);
        Button num_button4 = (Button) findViewById(R.id.num_4);
        Button num_button5 = (Button) findViewById(R.id.num_5);
        Button num_button6 = (Button) findViewById(R.id.num_6);
        Button num_button7 = (Button) findViewById(R.id.num_7);
        Button num_button8 = (Button) findViewById(R.id.num_8);
        Button num_button9 = (Button) findViewById(R.id.num_9);
        Button num_button0 = (Button) findViewById(R.id.num_0);
        Button accept_pass = (Button) findViewById(R.id.accept_pass);
        Button delete_char = (Button) findViewById(R.id.delete_char);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(num_button0);
        buttons.add(num_button1);
        buttons.add(num_button2);
        buttons.add(num_button3);
        buttons.add(num_button4);
        buttons.add(num_button5);
        buttons.add(num_button6);
        buttons.add(num_button7);
        buttons.add(num_button8);
        buttons.add(num_button9);
        buttons.add(delete_char);
        buttons.add(accept_pass);

        for (Button button : buttons) {
            button.setHeight(height / 5);
        }

        {
            View.OnClickListener but1_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString() + "1");
                }
            };
            View.OnClickListener but2_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"2");
                }
            };
            View.OnClickListener but3_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"3");
                }
            };
            View.OnClickListener but4_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"4");
                }
            };
            View.OnClickListener but5_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"5");
                }
            };
            View.OnClickListener but6_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"6");
                }
            };
            View.OnClickListener but7_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"7");
                }
            };
            View.OnClickListener but8_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"8");
                }
            };
            View.OnClickListener but9_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"9");
                }
            };
            View.OnClickListener but0_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass_text.setText(pass_text.getText().toString()+"0");
                }
            };
            View.OnClickListener butrem_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pass_text.setText(String.pass_text.getText().toString());
                }
            };
        }

    }
}