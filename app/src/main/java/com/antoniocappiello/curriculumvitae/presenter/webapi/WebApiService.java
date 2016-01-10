package com.antoniocappiello.curriculumvitae.presenter.webapi;

public class WebApiService {

    private final WebApi mWebApi;

    public WebApiService(WebApi webApi){
        mWebApi = webApi;
    }

    public void readAboutMe(){
        mWebApi.readAsset(
                "about_me.json",
                WebApiCallbackFactory.getAboutMeCallback());
    }

    public void readEducation(){
        mWebApi.readAsset(
                "education.json",
                WebApiCallbackFactory.getEducationCallback());
    }

    public void readWorkExperience(){
        mWebApi.readAsset(
                "work_experience.json",
                WebApiCallbackFactory.getWorkExperienceCallback());
    }
}
