package com.antoniocappiello.curriculumvitae;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init()
                .setMethodCount(1)
                .hideThreadInfo()
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }
}
