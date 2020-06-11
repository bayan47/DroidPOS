package com.example.shtrih;

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
}
