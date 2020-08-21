package com.example.shtrih;

final public class MultiApiHelper {

    public static String Package_name;

    public static void RunOnStartup()
    {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP){
            System.load("/data/data/"+Package_name+"/lib/libc++_shared.so");
            System.load("/data/data/"+Package_name+"/lib/libcppbase_fr_drv_ng.so");
            System.load("/data/data/"+Package_name+"/lib/libclassic_fr_drv_ng.so");
        }
    }
}
