package com.example.shtrih;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MenuSupportActivity extends AppCompatActivity {

    Navigation_Menu navigationLayout;
    RelativeLayout left_drawer;

    @Override
    public void setContentView (@LayoutRes int layoutIdRes)
    {
        super.setContentView(layoutIdRes);
        SetupMenu();
    }

    public void SetupMenu()
    {
        left_drawer = (RelativeLayout) (findViewById(R.id.left_drawer));
        navigationLayout = new Navigation_Menu(getApplicationContext(),left_drawer);
        left_drawer.addView(navigationLayout);
    }


}
