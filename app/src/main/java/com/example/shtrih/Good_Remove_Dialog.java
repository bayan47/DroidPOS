package com.example.shtrih;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Good_Remove_Dialog extends DialogFragment {

    Good good;
    ArrayList<Good> list_good;
    GoodsListAdapted adapted;
    Cashier_RMK activity;




    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        Dialog.OnClickListener positive_listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                list_good.remove(good);
                adapted.notifyDataSetChanged();
                activity.UpdateText();

            }
        };




        return builder.setTitle("Удаление позиции")
                .setMessage("Вы действительно хотите удалить "+"?")
                .setPositiveButton("Удалить", positive_listener)
                .setNegativeButton("Отмена",  null)
                .create();
    }




}