package com.example.shtrih;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.*;



import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;
//import ru.shtrih_m.fr_drv_ng.android_util.BuildConfig;

public class MainActivity extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Intent ToKassaSetting = new Intent(this,Kassa_SellUI.class);

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
        EditText ip = (EditText) findViewById(R.id.InputIpAddress);
        String ipaddress = ip.getText().toString();

        if(!ipaddress.isEmpty())
        {
            EditText tcp = (EditText) findViewById(R.id.InputTCPPort);
            String temp = tcp.getText().toString();

            if(!temp.isEmpty())
            {
                int tcpPort = Integer.parseInt(temp);

                EditText timeout_temp_1 = (EditText) findViewById(R.id.InputTimeout);
                String timeout_temp_2 = timeout_temp_1.getText().toString();

                if(!timeout_temp_2.isEmpty())
                {
                    int timeout = Integer.parseInt(timeout_temp_2);

                    Kassa.FrConnect(ipaddress, tcpPort, timeout);

                    int answer = Kassa.device.Connect();
                    if(answer == 0)
                    {
                        Kassa.device.Beep();
                        TextView statusConnection = (TextView) findViewById(R.id.textStatusConnection);
                        statusConnection.setText("Подключено");
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
}
