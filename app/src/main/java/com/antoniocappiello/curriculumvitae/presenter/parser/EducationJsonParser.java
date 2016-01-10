package com.antoniocappiello.curriculumvitae.presenter.parser;

import com.antoniocappiello.curriculumvitae.model.Education;
import com.antoniocappiello.curriculumvitae.presenter.DateUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.EDUCATION;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.LOGO_URL;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.NAME;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.PROVIDER;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.TYPE;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.YEAR;

public class EducationJsonParser {

    public static List<Education> parse(JsonObject json) throws JSONException {
        JsonArray educationJsonArray = json.get(EDUCATION).getAsJsonArray();
        List<Education> educationList = new ArrayList<>(educationJsonArray.size());
        for(JsonElement educationJsonElement : educationJsonArray){
            Education education = parseSingle(educationJsonElement.getAsJsonObject());
            educationList.add(education);
        }
        return educationList;
    }

    private static Education parseSingle(JsonObject json) {
        Date date = null;
        try {
            date = DateUtils.dateFromYear(json.has(YEAR) ? json.get(YEAR).getAsString() : "");
        } catch (ParseException e) {
            Logger.e(e.toString());
        }
        return new Education.Builder()
                .type(json.has(TYPE) ? json.get(TYPE).getAsString() : "")
                .provider(json.has(PROVIDER) ? json.get(PROVIDER).getAsString() : "")
                .logoUrl(json.has(LOGO_URL) ? json.get(LOGO_URL).getAsString() : "")
                .name(json.has(NAME) ? json.get(NAME).getAsString() : "")
                .year(date) //TODO TO TEST WHEN IS DATE NULL
                .build();
    }
}
