package com.example.shtrih;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;


import static android.content.Context.MODE_PRIVATE;

final public class DBHelper {

    public static SQLiteDatabase DataBase;

    public static String app_name;
    public static void OpenOrCreateDB(Context context)
    {

        try {
            DataBase = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("ps34.db"), null);
        }
        catch (SQLException sql)
        {

        }
        finally {
            DataBase = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory() +("/ps34.db"),null);
        }
 

    }



}
