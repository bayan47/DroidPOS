package com.example.shtrih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Navigation_Menu extends RelativeLayout {

    public Navigation_Menu(Context context, RelativeLayout parent)
    {
        super(context);
        initView(context,parent);
    }

    public void initView(final Context context,RelativeLayout parent)
    {
        // надуваем любой xml файл разметки
        View view= LayoutInflater.from(context).inflate(R.layout.navmenu_enclosure,parent,true);

    }


}

