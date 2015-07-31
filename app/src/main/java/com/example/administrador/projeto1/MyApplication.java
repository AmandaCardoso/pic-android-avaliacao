package com.example.administrador.projeto1;

import android.app.Application;

import com.example.administrador.projeto1.model.entities.AppUtil;

public class MyApplication  extends Application{
    @Override
    public void onCreate() {
        AppUtil.CONTEXT = getApplicationContext();
        super.onCreate();
    }
}
