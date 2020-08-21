package com.example.shtrih;

import java.io.Serializable;

public class Good implements Serializable {

    public String name;
    public long price; // цена товара в копейках
    public Kassa.PaymentItemType type;
    public Kassa.TaxTypes nds;
    public int isFreePrice; // 0 - жесткая цена, 1 - мягкая цена
    public int id;

    public Good()
    {

    }

    public Good (int ID, String Name,long Price,Kassa.TaxTypes NDS, Kassa.PaymentItemType Type, int FreePrice)
    {
        name = Name;
        price = Price;
        type = Type;
        nds = NDS;
        id = ID;
        isFreePrice = FreePrice;
    }

     public static Kassa.TaxTypes getNDS(int id)
     {
         Kassa.TaxTypes final_ndstype = Kassa.TaxTypes.WithoutNDS;

         for (Kassa.TaxTypes nds: Kassa.TaxTypes.values())
         {
             if (nds.nds_id==id)
             {
                 final_ndstype = nds;
             }
         }
         return  final_ndstype;
     }

     public static Kassa.PaymentItemType getpaytype(int id)
     {
         Kassa.PaymentItemType final_paytype = Kassa.PaymentItemType.Tovar;

         for (Kassa.PaymentItemType pay_type: Kassa.PaymentItemType.values())
         {
             if (pay_type.pay_item_type_id==id)
             {
                 final_paytype = pay_type;
             }
         }
         return  final_paytype;
     }
}
