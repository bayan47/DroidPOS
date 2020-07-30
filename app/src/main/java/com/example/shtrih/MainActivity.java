package com.example.shtrih;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import ru.shtrih_m.fr_drv_ng.android_util.BuildConfig;

public class MainActivity extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.

    public static String ipaddress;
    public static int port;
    public static int timeout;
    public static boolean created_table;
    public static SQLiteDatabase db;
    public static EditText ip ;
    public static EditText tcp;
    public static EditText timeout_temp_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ip = (EditText) findViewById(R.id.InputIpAddress);
        tcp = (EditText) findViewById(R.id.InputTCPPort);
        timeout_temp_1 = (EditText) findViewById(R.id.InputTimeout);

        DBHelper.OpenOrCreateDB(this);
        db = DBHelper.DataBase;
        db.execSQL("CREATE TABLE IF NOT EXISTS conn_settings (id INTEGER, ip TEXT, port INTEGER, timeout INTEGER)");
        Cursor query = db.rawQuery("SELECT * FROM conn_settings;", null);
        if(query.moveToFirst()){

            ipaddress = query.getString(1);
            port = query.getInt(2);
            timeout = query.getInt(3);
            created_table=true;
            ip.setText(ipaddress);
            tcp.setText(String.valueOf(port));
            timeout_temp_1.setText(String.valueOf(timeout));


        }


        final Intent ToKassaSetting = new Intent(this,Kassa_Settings.class);

        Button kassasettings_button = findViewById(R.id.kassasettings_button);
        View.OnClickListener kassaset = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ToKassaSetting);
            }
        };
        kassasettings_button.setOnClickListener(kassaset);
    }

    public void Connect_Button(View view)
    {

        ipaddress = ip.getText().toString();

        if(!ipaddress.isEmpty())
        {

            String temp = tcp.getText().toString();

            if(!temp.isEmpty())
            {
                port = Integer.parseInt(temp);


                String timeout_temp_2 = timeout_temp_1.getText().toString();

                if(!timeout_temp_2.isEmpty())
                {
                    timeout = Integer.parseInt(timeout_temp_2);

                    Kassa.FrConnect(ipaddress, port, timeout);

                    int answer = Kassa.device.Connect();
                    if(answer == 0)
                    {
                        Kassa.device.Beep();
                        TextView statusConnection = (TextView) findViewById(R.id.textStatusConnection);
                        statusConnection.setText("Подключено");

                        if(created_table==false)
                        {
                            db.execSQL("INSERT INTO conn_settings VALUES(0,'"+ipaddress+"',"+port+","+timeout+")");
                        }
                        else
                            db.execSQL("UPDATE conn_settings SET ip = '"+ipaddress+"',port = "+port+", timeout = "+timeout+" WHERE id = 0;");
                    }
                    else
                    {
                        TextView statusConnection = (TextView) findViewById(R.id.textStatusConnection);
                        statusConnection.setText("Ошибка");
                    }
                }

            }

        }

    }



    public void Disconnect_Button(View view)
    {
        Kassa.FrDisconnect();

        TextView statusConnection = (TextView) findViewById(R.id.textStatusConnection);
        statusConnection.setText("Отключено");
    }

    public String getDbPath(Context context, String YourDbName)
    {
        return context.getDatabasePath(YourDbName).getAbsolutePath();
    }
}
