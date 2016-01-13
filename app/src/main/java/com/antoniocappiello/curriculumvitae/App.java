/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 9:30 PM
 */

package com.antoniocappiello.curriculumvitae;

import android.app.Application;
import android.content.ContextWrapper;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.antoniocappiello.curriculumvitae.presenter.AssetUtils;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppComponent;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppModule;
import com.antoniocappiello.curriculumvitae.presenter.injector.DaggerAppComponent;
import com.antoniocappiello.curriculumvitae.presenter.parser.BookJsonParser;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.IOException;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class App extends Application {

    private static final String LIBRARY_DATABASE_INITIALIZED = "libDbInit";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.USE_CRASHLYTICS) {
            Fabric.with(this, new Crashlytics());
        }

        Logger.init()
                .setMethodCount(1)
                .hideThreadInfo()
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        initImageLoader();
        initDependencyInjection();
        FlowManager.init(this);
        initDatabase();
    }

    private void initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initDatabase() {
        if(!Prefs.getBoolean(LIBRARY_DATABASE_INITIALIZED, false)){
            try {
                String assetString = AssetUtils.readAsset(this, "library.json");
                JsonObject jsonObject = new Gson().fromJson(assetString, JsonElement.class).getAsJsonObject();
                List<Book> bookList = BookJsonParser.parse(jsonObject);
                appComponent().bookEntityOrchestrator().save(bookList);
                Prefs.putBoolean(LIBRARY_DATABASE_INITIALIZED, true);
            } catch (IOException e) {
                Logger.e(e.toString());
            }
        }
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
