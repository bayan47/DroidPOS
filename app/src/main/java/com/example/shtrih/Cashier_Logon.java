package com.example.shtrih;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Cashier_Logon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        MultiApiHelper.current_context = this;
        MultiApiHelper.Package_name = getPackageName();
        MultiApiHelper.RunOnStartup();

        DBHelper.OpenOrCreateDB(this);
        DBHelper.DataBase.execSQL("CREATE TABLE IF NOT EXISTS cashiers (id INTEGER PRIMARY KEY, cashier_FIO TEXT, cashier_INN TEXT, cashier_pass TEXT)");

        setContentView(R.layout.activity_cashier__logon);






        Button add_cashier = (Button) findViewById(R.id.add_cashier_button);



            View.OnClickListener but_add_cashier_listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout right_place = (LinearLayout) findViewById(R.id.right_place);
                    right_place.removeAllViews();

                    getSupportFragmentManager().beginTransaction().add(R.id.right_place, new Cashier_Add_Fragment()).commit();
                }
            };




        add_cashier.setOnClickListener(but_add_cashier_listener);

        final ListView cashiers_list = (ListView) findViewById(R.id.Cashiers_list);

        cashiers_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout right_place = (LinearLayout) findViewById(R.id.right_place);
                right_place.removeAllViews();
                getSupportFragmentManager().beginTransaction().add(R.id.right_place,new Cashier_NumPad_Fragment()).commit();
                Cashier cashier_selected = (Cashier) cashiers_list.getItemAtPosition(i);
                Selected_Cashier.selected_cashier_pass = cashier_selected.password;

                Selected_Cashier.selected_cashier = (Cashier) cashiers_list.getItemAtPosition(i);
                view.setBackgroundColor(Color.rgb(205,237,253));
            }
        });

        ArrayList<Cashier> cashiers = new ArrayList<>();

        Cursor query = DBHelper.DataBase.rawQuery("SELECT * FROM cashiers;", null);
        if(query.moveToFirst())
        {

            do {
                int cashier_id = query.getInt(0);
                String cashier_FIO = query.getString(1);
                String cashier_INN = query.getString(2);
                String cashier_pass = query.getString(3);
                cashiers.add(new Cashier(cashier_id,cashier_FIO,cashier_INN,cashier_pass));
            }
            while (query.moveToNext());
        }



        ArrayAdapter<Cashier> cashiers_list_adapter = new ArrayAdapter<Cashier>(this,android.R.layout.simple_list_item_1,cashiers);
        cashiers_list.setAdapter(cashiers_list_adapter);
    }
}