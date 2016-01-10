package com.antoniocappiello.curriculumvitae.controller;

public class WebApiController {

    private final WebApi mWebApi;

    public WebApiController(WebApi webApi){
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
