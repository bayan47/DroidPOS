package com.example.shtrih;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cashier_Add_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cashier_Add_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cashier_Add_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_content.
     */
    // TODO: Rename and change types and number of parameters
    public static Cashier_Add_Fragment newInstance(String param1, String param2) {
        Cashier_Add_Fragment fragment = new Cashier_Add_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.cashier_add_fragment, container, false);
        final Button save_cashier_button = (Button) view.findViewById(R.id.AddCashier);
        final EditText cashier_name_text = (EditText) view.findViewById(R.id.FIOCashier);
        final EditText cashier_INN_text = (EditText) view.findViewById(R.id.INNCashier);
        final EditText cashier_pass_text = (EditText) view.findViewById(R.id.CashierPass);

        final Intent goto_logon = new Intent(getContext(),Cashier_Logon.class);

        View.OnClickListener save_cashier_button_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cashier_name_text.getText().toString().isEmpty()&&!cashier_INN_text.getText().toString().isEmpty()&&!cashier_pass_text.getText().toString().isEmpty())
                {
                    String sql_text = String.format("INSERT INTO cashiers (cashier_FIO,cashier_INN,cashier_pass) VALUES ('%s','%s','%s');", cashier_name_text.getText().toString(), cashier_INN_text.getText().toString(), cashier_pass_text.getText().toString());
                    DBHelper.DataBase.execSQL(sql_text);
                    startActivity(goto_logon);
                }

                else
                {
                    Toast.makeText(getContext(),"Не заполнены все поля",Toast.LENGTH_LONG).show();
                }
            }
        };

        save_cashier_button.setOnClickListener(save_cashier_button_listener);

        return view;
    }
}