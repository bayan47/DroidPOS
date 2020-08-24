package com.example.shtrih;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cashier_NumPad_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cashier_NumPad_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cashier_NumPad_Fragment() {
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
    public static Cashier_NumPad_Fragment newInstance(String param1, String param2) {
        Cashier_NumPad_Fragment fragment = new Cashier_NumPad_Fragment();
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

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        final Intent goto_cashierRMK = new Intent(getContext(),Cashier_RMK.class);
        final Intent goto_logon = new Intent(getContext(),Cashier_Logon.class);


        View view = inflater.inflate(R.layout.cashier_logon_numpad, container, false);

        final EditText pass_text = (EditText) view.findViewById(R.id.cashier_pass);
        Button num_button1 = (Button) view.findViewById(R.id.num_1);
        Button num_button2 = (Button) view.findViewById(R.id.num_2);
        Button num_button3 = (Button) view.findViewById(R.id.num_3);
        Button num_button4 = (Button) view.findViewById(R.id.num_4);
        Button num_button5 = (Button) view.findViewById(R.id.num_5);
        Button num_button6 = (Button) view.findViewById(R.id.num_6);
        Button num_button7 = (Button) view.findViewById(R.id.num_7);
        Button num_button8 = (Button) view.findViewById(R.id.num_8);
        Button num_button9 = (Button) view.findViewById(R.id.num_9);
        Button num_button0 = (Button) view.findViewById(R.id.num_0);
        Button accept_pass = (Button) view.findViewById(R.id.accept_pass);
        Button delete_char = (Button) view.findViewById(R.id.delete_char);
        Button delete_cashier = (Button) view.findViewById(R.id.delete_cashier);

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
        buttons.add(delete_cashier);

        for (Button button : buttons) {
            button.setHeight(height / 5);
        }


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
                if (pass_text.getText().length()>0)
                {
                    pass_text.setText(pass_text.getText().toString().substring(0, pass_text.getText().length() - 1));
                }
            }
        };

        View.OnClickListener but_accept_pass_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass_text.getText().length()>0)
                {
                    if (pass_text.getText().toString().equals(Selected_Cashier.selected_cashier_pass))
                    {
                        //Kassa.AutoConnect();
                        startActivity(goto_cashierRMK);

                    }
                    else
                    {
                        Toast.makeText(getContext(),"Доступ запрещен", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    Toast.makeText(getContext(),"Введите пароль", Toast.LENGTH_SHORT).show();
                }
            }
        };

        View.OnClickListener but_delete_cashier = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql_text = String.format("DELETE FROM cashiers WHERE id=%d;",Selected_Cashier.selected_cashier.id);
                DBHelper.DataBase.execSQL(sql_text);

                startActivity(goto_logon);
            }
        };

        num_button1.setOnClickListener(but1_listener);
        num_button2.setOnClickListener(but2_listener);
        num_button3.setOnClickListener(but3_listener);
        num_button4.setOnClickListener(but4_listener);
        num_button5.setOnClickListener(but5_listener);
        num_button6.setOnClickListener(but6_listener);
        num_button7.setOnClickListener(but7_listener);
        num_button8.setOnClickListener(but8_listener);
        num_button9.setOnClickListener(but9_listener);
        num_button0.setOnClickListener(but0_listener);
        delete_char.setOnClickListener(butrem_listener);
        accept_pass.setOnClickListener(but_accept_pass_listener);
        delete_cashier.setOnClickListener(but_delete_cashier);

        return view;
    }
}