package com.antoniocappiello.curriculumvitae.presenter.event;

import com.antoniocappiello.curriculumvitae.model.Education;

import java.util.List;

public class EducationReceivedEvent {
    private final List<Education> mEducationList;

    public EducationReceivedEvent(List<Education> educationList) {
        mEducationList = educationList;
    }

    public List<Education> getEducationList() {
        return mEducationList;
    }
}
