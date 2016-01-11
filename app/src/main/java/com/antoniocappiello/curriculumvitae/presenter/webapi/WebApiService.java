package com.antoniocappiello.curriculumvitae.presenter.webapi;

import com.google.gson.JsonElement;

import rx.Observable;

public class WebApiService {

    private final WebApi mWebApi;

    public WebApiService(WebApi webApi){
        mWebApi = webApi;
    }

    public Observable<JsonElement> readAboutMe(){
        return mWebApi.readAssetWithObservable(
                "about_me.json");
    }

    public void readEducation(){
        mWebApi.readAssetWithCallback(
                "education.json",
                WebApiCallbackFactory.getEducationCallback());
    }

    public void readWorkExperience(){
        mWebApi.readAssetWithCallback(
                "work_experience.json",
                WebApiCallbackFactory.getWorkExperienceCallback());
    }
}
