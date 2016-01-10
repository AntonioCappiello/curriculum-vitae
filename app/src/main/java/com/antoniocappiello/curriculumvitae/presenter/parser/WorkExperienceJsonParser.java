package com.antoniocappiello.curriculumvitae.presenter.parser;

import com.antoniocappiello.curriculumvitae.model.WorkExperience;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.COMPANY;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.DESCRIPTION;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.END;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.KEY_WORDS;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.LOGO_URL;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.ROLE;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.START;
import static com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiFields.WORK_EXPERIENCE;

public class WorkExperienceJsonParser {

    public static List<WorkExperience> parse(JsonObject json) throws JSONException {
        JsonArray workExperienceJsonArray = json.get(WORK_EXPERIENCE).getAsJsonArray();
        List<WorkExperience> workExperienceList = new ArrayList<>(workExperienceJsonArray.size());
        for(JsonElement workExperienceJsonElement : workExperienceJsonArray){
            WorkExperience workExperience = parseSingle(workExperienceJsonElement.getAsJsonObject());
            workExperienceList.add(workExperience);
        }
        return workExperienceList;
    }

    private static WorkExperience parseSingle(JsonObject json) {
        return new WorkExperience.Builder()
                .role(json.has(ROLE) ? json.get(ROLE).getAsString() : "")
                .company(json.has(COMPANY) ? json.get(COMPANY).getAsString() : "")
                .logoUrl(json.has(LOGO_URL) ? json.get(LOGO_URL).getAsString() : "")
                .start(json.has(START) ? json.get(START).getAsString() : "")
                .end(json.has(END) ? json.get(END).getAsString() : "")
                .description(json.has(DESCRIPTION) ? json.get(DESCRIPTION).getAsString() : "")
                .keyWords(json.has(KEY_WORDS) ? json.get(KEY_WORDS).getAsString() : "")
                .build();
    }
}
