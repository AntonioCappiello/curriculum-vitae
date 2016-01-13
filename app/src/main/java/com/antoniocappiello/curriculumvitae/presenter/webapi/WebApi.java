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