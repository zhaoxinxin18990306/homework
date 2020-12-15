package com.example.androidshopping;

import android.app.Application;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

public class MyOwnApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

    }
}
