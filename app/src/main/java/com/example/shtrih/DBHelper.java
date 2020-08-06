package com.example.shtrih;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;


import static android.content.Context.MODE_PRIVATE;

final public class DBHelper {

    public static SQLiteDatabase DataBase;

    public static String app_name;
    public static void OpenOrCreateDB(Context context)
    {

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP){
            DataBase = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory() +("/ps34.db"),null);
        } else{
            DataBase = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("ps34.db") ,null);
        }

    }



}
