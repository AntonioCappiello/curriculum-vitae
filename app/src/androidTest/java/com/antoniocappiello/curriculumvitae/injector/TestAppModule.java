package com.antoniocappiello.curriculumvitae.injector;

import android.content.Context;

import com.antoniocappiello.curriculumvitae.mock.MockWebApi;
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

}