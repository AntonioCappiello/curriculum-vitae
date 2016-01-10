package com.antoniocappiello.curriculumvitae.controller;

import com.antoniocappiello.curriculumvitae.BuildConfig;

import retrofit.RestAdapter;

public class WebApiClientFactory {

    public WebApi getClient(){
        return new RestAdapter.Builder()
                .setEndpoint(BuildConfig.WEB_API_APP_SERVICE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(WebApi.class);
    }
}
