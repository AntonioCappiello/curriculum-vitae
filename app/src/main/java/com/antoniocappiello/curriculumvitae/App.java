package com.antoniocappiello.curriculumvitae;

import android.app.Application;
import android.content.ContextWrapper;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.antoniocappiello.curriculumvitae.presenter.AssetUtils;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookEntityOrchestrator;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookSaver;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookValidator;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppComponent;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppModule;
import com.antoniocappiello.curriculumvitae.presenter.injector.DaggerAppComponent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

public class App extends Application {

    private static final String LIBRARY_DATABASE_INITIALIZED = "libDbInit";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

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

    public AppComponent appComponent() {
        return appComponent;
    }

    private void initDatabase() {

        if(Prefs.getBoolean(LIBRARY_DATABASE_INITIALIZED, false)){
            return;
        }

        try {
            BookEntityOrchestrator bookEntityOrchestrator = new BookEntityOrchestrator(new BookValidator(), new BookSaver()); //TODO iject via dagger as example
            String assetString = AssetUtils.readAsset(this, "library.json");
            JsonObject jsonObject = new Gson().fromJson(assetString, JsonElement.class).getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("book").getAsJsonArray();
            for(JsonElement jsonElement:jsonArray){
                JsonObject bookAsJsonObject = jsonElement.getAsJsonObject();
                Book book = new Book(bookAsJsonObject.get("name").getAsString(),
                        bookAsJsonObject.get("author").getAsString(),
                        bookAsJsonObject.get("coverImageUrl").getAsString()
                );
                bookEntityOrchestrator.save(book);

            }
            Prefs.putBoolean(LIBRARY_DATABASE_INITIALIZED, true);


        } catch (IOException e) {
            Logger.e(e.toString());
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
}
