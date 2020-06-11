package com.example.shtrih;

import android.widget.EditText;
import android.widget.TextView;

import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;

final public class Kassa  {


    public static classic_interface device = new classic_interface();

    public static void OpenSession()           //Открыть смену на кассу
    {
        device.FNOpenSession();
    }

    public static void CloseSession()          //Закрыть смену на кассе
    {
        device.FNCloseSession();
    }

    public static void XPrint()
    {
        device.PrintReportWithoutCleaning();
    }

    public static void FrConnect(String address, int port, int timeout)            //Подключить кассу
    {
        Kassa.device.Set_ConnectionType(classic_interface.TConnectionType.Tcp);
        Kassa.device.Set_UseIPAddress(true);
        Kassa.device.Set_IPAddress(address);
        Kassa.device.Set_TCPPort(port);
        Kassa.device.Set_Timeout(timeout);
        Kassa.device.Set_ProtocolType(0);
        Kassa.device.Set_Password(30);
    }

    public static void FrDisconnect()   // Отключить кассу
    {
        Kassa.device.Disconnect();
        int answer = Kassa.device.Connect();
    }

}
