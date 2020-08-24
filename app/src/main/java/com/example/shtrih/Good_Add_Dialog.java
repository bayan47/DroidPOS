package com.example.shtrih;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Good_Add_Dialog extends DialogFragment {


    DialogInterface.OnClickListener negative_listener=null;
    String header_text;
    long price;
    Good good;
    ArrayList<Good> goods_to_check;
    EditText good_price_text;
    GoodsListAdapted adapted;
    EditText count_good_text;
    ImageButton more_count;
    ImageButton less_count;



    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        DialogInterface.OnClickListener positive_listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                good.count = Float.parseFloat(count_good_text.getText().toString());
                good.price = Kassa.ParsePrice(good_price_text.getText().toString());
                goods_to_check.add(good);
                adapted.notifyDataSetChanged();

            }
        };



        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_good_add,null);

        more_count = (ImageButton) v.findViewById(R.id.upper_count_button);
        less_count = (ImageButton) v.findViewById(R.id.lower_count_button);
        good_price_text = (EditText) v.findViewById(R.id.price_good_text);
        count_good_text = (EditText) v.findViewById(R.id.count_good_text);


        good_price_text.setText(price==0?"0.00":String.valueOf((price/100)));
        count_good_text.setText("1.00");


        ImageButton.OnClickListener more_count_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float count_good;
                count_good = Float.parseFloat(count_good_text.getText().toString());
                count_good +=1;
                count_good_text.setText(String.valueOf(count_good));
            }
        };

        ImageButton.OnClickListener less_count_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float count_good = Float.parseFloat(count_good_text.getText().toString());
                if (count_good>0)
                {
                    count_good-=1;
                    count_good_text.setText(String.valueOf(count_good));
                }
            }
        };

        more_count.setOnClickListener(more_count_listener);
        less_count.setOnClickListener(less_count_listener);

        return builder.setTitle(header_text)
                .setView(v)
                .setPositiveButton("Добавить", positive_listener)
                .setNegativeButton("Отмена",  negative_listener)
                .create();
    }




}