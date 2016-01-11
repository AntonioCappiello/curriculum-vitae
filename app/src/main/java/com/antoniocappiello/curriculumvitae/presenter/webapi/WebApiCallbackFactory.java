package com.antoniocappiello.curriculumvitae.presenter.webapi;

import com.antoniocappiello.curriculumvitae.model.Education;
import com.antoniocappiello.curriculumvitae.model.WorkExperience;
import com.antoniocappiello.curriculumvitae.presenter.event.EducationReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.WorkExperienceReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.parser.EducationJsonParser;
import com.antoniocappiello.curriculumvitae.presenter.parser.WorkExperienceJsonParser;
import com.google.gson.JsonElement;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WebApiCallbackFactory {

    public static Callback<JsonElement> getEducationCallback() {
        return new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                try {
                    List<Education> educationList = EducationJsonParser.parse(jsonElement.getAsJsonObject());
                    EventBus.getDefault().post(new EducationReceivedEvent(educationList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                try {
                    List<WorkExperience> workExperienceList = WorkExperienceJsonParser.parse(jsonElement.getAsJsonObject());
                    EventBus.getDefault().post(new WorkExperienceReceivedEvent(workExperienceList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.e(error.toString());
            }
        };
    }
}
