/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 6:05 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.injector;

import android.content.Context;

import com.antoniocappiello.curriculumvitae.BuildConfig;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookEntityOrchestrator;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookSaver;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookValidator;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context provideAppContext() {
        return context;
    }

    @Provides
    public WebApi provideWebApi() {
        return new RestAdapter.Builder()
                .setEndpoint(BuildConfig.WEB_API_APP_SERVICE_URL)
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new Gson()))
                .build()
                .create(WebApi.class);
    }

    @Provides
    public WebApiService provideWebApiService(WebApi webApi){
        return new WebApiService(webApi);
    }

    @Provides
    public BookValidator provideBookValidator(){
        return new BookValidator();
    }

    @Provides
    public BookSaver provideBookSaver(){
        return new BookSaver();
    }

    @Provides
    public BookEntityOrchestrator provideBookEntityOrchestrator(BookValidator bookValidator, BookSaver bookSaver){
        return new BookEntityOrchestrator(new BookValidator(), new BookSaver());
    }

}