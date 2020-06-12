package com.example.shtrih;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.widget.Toast;
import java.net.*;



import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;
//import ru.shtrih_m.fr_drv_ng.android_util.BuildConfig;

public class MainActivity extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Kassa.device.Set_ConnectionType(classic_interface.TConnectionType.Tcp);
        Kassa.device.Set_UseIPAddress(true);
        Kassa.device.Set_IPAddress("91.202.207.241");
        Kassa.device.Set_TCPPort(8888);
        Kassa.device.Set_Timeout(10000);
        Kassa.device.Set_ProtocolType(0);
        Kassa.device.Set_Password(30);
        int answer = Kassa.device.Connect();
        Kassa.device.Beep();

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
}
