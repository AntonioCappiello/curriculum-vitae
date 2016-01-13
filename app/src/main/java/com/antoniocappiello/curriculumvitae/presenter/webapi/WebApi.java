/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 11:26 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.webapi;

import com.antoniocappiello.curriculumvitae.model.AboutMe;
import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface WebApi {

    @GET("/assets/{fileName}")
    void readAssetWithCallback(
            @Path("fileName") String fileName,
            Callback<JsonElement> callback);


    @GET("/assets/{fileName}")
    Observable<AboutMe> readAssetWithObservable(
            @Path("fileName") String fileName);

}