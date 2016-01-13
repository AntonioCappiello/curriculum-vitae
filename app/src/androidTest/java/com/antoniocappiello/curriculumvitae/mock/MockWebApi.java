/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 11:26 PM
 */

package com.antoniocappiello.curriculumvitae.mock;

import com.antoniocappiello.curriculumvitae.model.AboutMe;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.http.Path;
import rx.Observable;

public class MockWebApi implements WebApi {
    @Override
    public void readAssetWithCallback(@Path("fileName") String fileName, Callback<JsonElement> callback) {

    }

    @Override
    public Observable<AboutMe> readAssetWithObservable(@Path("fileName") String fileName) {
        AboutMe aboutMe = new Gson().fromJson(TestData.ABOUT_ME_JSON, AboutMe.class);
        return Observable.just(aboutMe).delay(1, TimeUnit.SECONDS);
    }
}
