package com.example.shtrih;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.AsyncTask;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.widget.Toast;
import java.net.*;



import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;
//import ru.shtrih_m.fr_drv_ng.android_util.BuildConfig;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    // Used to load the 'native-lib' library on application startup.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method

        tv = findViewById(R.id.sample_text);

        final classic_interface ci = new classic_interface();
        ci.Set_ConnectionType(classic_interface.TConnectionType.Tcp);
        ci.Set_UseIPAddress(true);
        ci.Set_IPAddress("192.168.34.35");
        ci.Set_TCPPort(8888);
        ci.Set_Timeout(10000);
        ci.Set_ProtocolType(0);
        int answer = ci.Connect();
        ci.Beep();
        ci.Beep();
        ci.Beep();
        ci.Beep();
        ci.Beep();ci.Beep();

        ci.Disconnect();

        class MyTask extends AsyncTask<Void,Void,Void> {
            public boolean result;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... voids) {
                result=isHostAvailable("192.168.34.35",8888,10000);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (result==true)
                {
                    tv.setText("Успешно");
                }
                else
                {
                    tv.setText("Не успешно");
                }
            }
        }
       // MyTask my = new MyTask();
       // my.execute();


    }






    public static boolean isHostAvailable(final String host, final int port, final int timeout) {
        try (final Socket socket = new Socket()) {
            final InetAddress inetAddress = InetAddress.getByName(host);
            final InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, port);

            socket.connect(inetSocketAddress, timeout);
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

