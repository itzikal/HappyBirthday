package com.alkotzer.itzik.happybirthday.general;

import android.app.Application;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class HappyBirthdayApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        AppManager.init(getApplicationContext());
    }
}
