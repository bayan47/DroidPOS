package com.example.shtrih;

import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;

final public class Kassa  {

    public static classic_interface device = new classic_interface(); //Физическая касса

    public static enum TaxTypes         //Перечисление типов НДС
    {
        WithoutNDS(0,"Без НДС"),NDS20(1,"НДС 20%"),NDS10(2,"НДС 10%"),NDS0(3,"НДС 0%"),WithoutNDS2(4,"Без НДС"),NDS20120(5, "НДС 20/120"),NDS10110(6, "НДС 10/110");
        int nds_id;
        String nds_name;
        TaxTypes(int id, String name){
            nds_id=id;
        }
    }
    public static double quantity; // Количество товара
    public static TaxTypes taxType; //Тип НДС


    public static void OpenSession()           //Открыть смену на кассу
    {
        device.FNOpenSession();
    }

    public static void CloseSession()          //Закрыть смену на кассе
    {
        device.FNCloseSession();
    }

    public static void XPrint()                 //Печать X-отчета
    {
        device.PrintReportWithoutCleaning();
    }

    public static void Sell()                   //Продажа
    {
        device.Set_CheckType(1);           //Устанавливаю тип операции. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1ga8c8729c0e051e112febacd4f7f9ee91d
        device.Set_Quantity(quantity);           //Установка количества товара к продаже
        device.Set_TaxValue1Enabled(false);      //Установка самостоятельного расчета суммы налога. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1gab43c70068f2161777d97b26b7b586c52
        device.Set_Summ1Enabled(false);   //Установка параметра Summ1. (Нихрена не понятно для чего, но когда отключен сумма считается - количество товара * чек.

        device.FNOperation();
    }
}
