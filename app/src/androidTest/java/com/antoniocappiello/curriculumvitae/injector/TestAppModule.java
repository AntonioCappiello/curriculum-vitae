/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 6:46 PM
 */

package com.antoniocappiello.curriculumvitae.injector;

import android.content.Context;

import com.antoniocappiello.curriculumvitae.mock.MockWebApi;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookEntityOrchestrator;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookSaver;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookValidator;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppScope;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;

import dagger.Module;
import dagger.Provides;


@Module
public class TestAppModule {

    private final Context context;

    public TestAppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context provideAppContext() {
        return context;
    }

    @Provides
    public WebApi provideWebApi() {
        return new MockWebApi();
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