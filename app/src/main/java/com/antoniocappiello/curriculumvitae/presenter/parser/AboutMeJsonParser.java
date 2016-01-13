package com.antoniocappiello.curriculumvitae.presenter.parser;

import com.antoniocappiello.curriculumvitae.model.AboutMe;
import com.google.gson.JsonObject;

import org.json.JSONException;

import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.CAREER;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.FREE_TIME;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.NAME;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.NATIONALITY;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.OVERVIEW;

public class AboutMeJsonParser {

    public static AboutMe parse(JsonObject json) throws JSONException {
        return new AboutMe.Builder()
                .name(json.has(NAME) ? json.get(NAME).getAsString() : "")
                .nationality(json.has(NATIONALITY) ? json.get(NATIONALITY).getAsString() : "")
                .overview(json.has(OVERVIEW) ? json.get(OVERVIEW).getAsString() : "")
                .career(json.has(CAREER) ? json.get(CAREER).getAsString() : "")
                .freeTime(json.has(FREE_TIME) ? json.get(FREE_TIME).getAsString() : "")
                .build();
    }
}
