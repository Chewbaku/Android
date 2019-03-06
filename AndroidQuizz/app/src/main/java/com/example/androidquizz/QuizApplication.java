package com.example.androidquizz;

import android.app.Application;
import android.content.Context;

public class QuizApplication extends Application {
    private static Context sContext;

    public static Context getContext(){return sContext;}

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
