package com.antoniocappiello.curriculumvitae.controller;

import com.google.gson.JsonElement;
import com.orhanobut.logger.Logger;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WebApiCallbackFactory {
    
    public static Callback<JsonElement> getAboutMeCallback() {
        return new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Logger.e(jsonElement.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.e(error.toString());
            }
        };
    }

    public static Callback<JsonElement> getEducationCallback() {
        return new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Logger.e(jsonElement.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.e(error.toString());
            }
        };
    }

    public static Callback<JsonElement> getWorkExperienceCallback() {
        return new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Logger.e(jsonElement.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.e(error.toString());
            }
        };
    }
}
