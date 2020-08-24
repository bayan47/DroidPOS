package com.example.shtrih;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import ru.shtrih_m.fr_drv_ng.classic_interface.classic_interface;

final public class Kassa  {


    public static classic_interface device = new classic_interface(); //Физическая касса
    public static ArrayList<Good> goods_to_sell_list = new ArrayList<Good>();

    public static enum TaxTypes         //Перечисление типов НДС
    {
        WithoutNDS(0,"Без НДС"),NDS20(1,"НДС 20%"),NDS10(2,"НДС 10%"),NDS0(3,"НДС 0%"),WithoutNDS2(4,"Без НДС"),NDS20120(5, "НДС 20/120"),NDS10110(6, "НДС 10/110");
        int nds_id;
        String nds_name;
        TaxTypes(int id, String name){
            this.nds_id=id;
            this.nds_name = name;
        }

        @Override
        public String toString()
        {
            super.toString();
            return nds_name;
        }

    }

    public static enum PaymentTypes     //Перечисление признаков способов расчета
    {
        Predoplata(1,"Предоплата 100%"),NotFullPredoplata(2,"Частичная предоплата"),Avans(3, "Аванс"),FullRashet(4,"Полный расчет"),NotFullRashet(5,"Частичный расчет"),PeredachaKredit(6,"Передача в кредит"),OplataKredit(7,"Оплата в кредит");
        int pay_id;
        String pay_name;

        PaymentTypes(int id,String name)
        {
            pay_id = id;
            pay_name = name;
        }
        @Override
        public String toString()
        {
            super.toString();
            return pay_name;
        }
    }

    public static enum PaymentItemType
    {

        Tovar(1,"Товар"),AkcizTovar(2,"Подакцизный товар"),Rabota(3,"Работа"),Usluga(4,"Услуга"),StavkaAzart(5,"Ставка азартной игры"),IncomeAzart(6,"Выигрыш азартной игры"),LotBilet(7,"Лотерейный билет"),IncomeLot(8,"Выигрыш лотереи"),RID(9,"Предоставление РИД"),Platezh(10,"Платеж"),IncomeAgent(11,"Агентское вознаграждение"),SostavPayItemType(12,"Составной предмет расчета"),AnotherpayItemType(13,"Иной предмет расчета");

        int pay_item_type_id;
        String pay_item_type_name;

        PaymentItemType(int id, String name)
        {
            pay_item_type_id = id;
            pay_item_type_name = name;
        }

        @Override
        public String toString()
        {
            super.toString();
            return pay_item_type_name;
        }
    }

    public static enum CashOperationType
    {
        Prihod(1,"Приход"),VozvratPrihoda(2,"Возврат прихода"),Rashod(3, "Расход"),VozvratRashoda(4,"Возврат расхода");

        int cash_operation_type_id;
        String cash_operation_type_name;

        CashOperationType(int id, String name)
        {
            cash_operation_type_id = id;
            cash_operation_type_name = name;
        }

        @Override
        public String toString()
        {
            super.toString();
            return cash_operation_type_name;
        }



    }

    public static String goodName; //Название товара
    public static long price; //Цена товара
    public static float quantity; // Количество товара
    public static TaxTypes taxType; //Тип НДС
    public static PaymentTypes paymentType; //Признак способа расчета
    public static PaymentItemType paymentItemType; //Признак предмета расчета
    public static CashOperationType cashOperationType; //Тип кассовой операции

    public static int ErrorCode; //код ошибки


    public static void FlushTableParameters()
    {
        Kassa.device.Set_TableNumber(0);
        Kassa.device.Set_RowNumber(0);
        Kassa.device.Set_FieldNumber(0);
    }

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
    }

    public static void CashOperation()                   //Продажа
    {
        device.Set_CheckType(cashOperationType.cash_operation_type_id);           //Устанавливаю тип операции. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1ga8c8729c0e051e112febacd4f7f9ee91d
        device.Set_Price(price);
        device.Set_StringForPrinting(goodName);
        device.Set_Quantity(quantity);           //Установка количества товара к продаже
        device.Set_TaxValue1Enabled(false);      //Установка самостоятельного расчета суммы налога. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1gab43c70068f2161777d97b26b7b586c52
        device.Set_Summ1(device.Get_Price()*Double.valueOf(device.Get_Quantity()).longValue());
        device.Set_Summ1Enabled(false);   //Установка параметра Summ1. (Нихрена не понятно для чего, но когда отключен сумма считается - количество товара * чек.
        device.Set_Tax1(taxType.nds_id);        //Установка выбранного НДС
        device.Set_Department(1);         //Установка режима свободной продажи - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1ga25e8f3455a02458e60cbd624000c751b
        device.Set_PaymentTypeSign(paymentType.pay_id); //Установка признака расчета
        device.Set_PaymentItemSign(paymentItemType.pay_item_type_id); //Установка признака предмета расчета
        device.FNOperation();
        device.FNCloseCheckEx();
    }

    public static void CashOperation2(ArrayList<Good> good_list)                   //Продажа
    {
        long Summ1=0;
        for (Good good:good_list)
        {
            quantity = good.count;
            device.Set_CheckType(CashOperationType.Prihod.cash_operation_type_id);           //Устанавливаю тип операции. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1ga8c8729c0e051e112febacd4f7f9ee91d
            device.Set_Price(good.price);
            device.Set_StringForPrinting(good.name);

            device.Set_Quantity(quantity);           //Установка количества товара к продаже
            device.Set_TaxValue1Enabled(false);      //Установка самостоятельного расчета суммы налога. Подробнее - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1gab43c70068f2161777d97b26b7b586c52


            Summ1 = Summ1 + (long)(good.price*quantity);

            device.Set_Summ1Enabled(false);   //Установка параметра Summ1. (Нихрена не понятно для чего, но когда отключен сумма считается - количество товара * чек.
            device.Set_Tax1(good.nds.nds_id);        //Установка выбранного НДС
            device.Set_Department(1);         //Установка режима свободной продажи - https://github.com/shtrih-m/fr_drv_ng/wiki/Properties#group___properties_1ga25e8f3455a02458e60cbd624000c751b
            device.Set_PaymentTypeSign(PaymentTypes.FullRashet.pay_id); //Установка признака расчета
            device.Set_PaymentItemSign(good.type.pay_item_type_id); //Установка признака предмета расчета
            device.FNOperation();
        }
        device.Set_Summ1(Summ1);
        device.FNCloseCheckEx();
    }

    public static long ParsePrice(String price_string)  //Метод округляющий цену до двух знаков после запятых и приводящий цену в копейки. Касса работает с копейками.
    {
        BigDecimal bd = new BigDecimal(price_string);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double input_price = bd.doubleValue()*100;
        return Double.valueOf(input_price).longValue();
    }

    public static void AutoConnect()
    {
        SQLiteDatabase db = DBHelper.DataBase;
        Cursor query = db.rawQuery("SELECT * FROM conn_settings;", null);

        String ipaddress;
        int port;
        int timeout;

        if(query.moveToFirst())
        {
            ipaddress = query.getString(1);
            port = query.getInt(2);
            timeout = query.getInt(3);
            Kassa.FrConnect(ipaddress, port, timeout);
            int ans = Kassa.device.Connect();
            Kassa.device.Beep();
        }
    }

}
