package com.example.shtrih;

import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;

final public class MultiApiHelper {

    public static String Package_name;

    public static float roundFloat(float f, int places) {

        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }

    public static void RunOnStartup()
    {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP){
            System.load("/data/data/"+Package_name+"/lib/libc++_shared.so");
            System.load("/data/data/"+Package_name+"/lib/libcppbase_fr_drv_ng.so");
            System.load("/data/data/"+Package_name+"/lib/libclassic_fr_drv_ng.so");
        }
    }

    public static Context current_context;


}
