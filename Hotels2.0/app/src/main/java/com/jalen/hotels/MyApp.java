package com.jalen.hotels;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 */

public class MyApp extends Application {

    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //bmob后台申请的应用id https://www.bmob.cn/app/secret/196234
        Bmob.initialize(this, "c4e4102a46a781f35deb915e1fee55e3");
    }

    public static MyApp getInstance() {
        return instance;
    }
}
