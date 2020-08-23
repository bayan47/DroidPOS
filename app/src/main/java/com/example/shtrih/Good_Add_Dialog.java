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



    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        DialogInterface.OnClickListener positive_listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                good.count = Float.parseFloat(count_good_text.getText().toString());
                good.price = Long.parseLong(good_price_text.getText().toString())*100;
                goods_to_check.add(good);
                adapted.notifyDataSetChanged();
                Toast.makeText(getContext(),"kek",Toast.LENGTH_SHORT).show();
            }
        };

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_good_add,null);

        good_price_text = (EditText) v.findViewById(R.id.price_good_text);
        count_good_text = (EditText) v.findViewById(R.id.count_good_text);


        good_price_text.setText(price==0?"0.00":String.valueOf(price/100));
        count_good_text.setText("1.00");

        return builder.setTitle(header_text)
                .setView(v)
                .setPositiveButton("Добавить", positive_listener)
                .setNegativeButton("Отмена",  negative_listener)
                .create();
    }




}