/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 11:26 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.webapi;

import com.antoniocappiello.curriculumvitae.model.AboutMe;

import rx.Observable;

public class WebApiService {

    private final WebApi mWebApi;

    public WebApiService(WebApi webApi){
        mWebApi = webApi;
    }

    public Observable<AboutMe> readAboutMe(){
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
