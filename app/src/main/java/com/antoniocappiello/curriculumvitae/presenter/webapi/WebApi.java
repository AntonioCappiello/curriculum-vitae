package com.antoniocappiello.curriculumvitae.presenter.webapi;

import com.google.gson.JsonElement;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface WebApi {

    @GET("/assets/{fileName}")
    void readAsset(
            @Path("fileName") String fileName,
            Callback<JsonElement> callback);

}