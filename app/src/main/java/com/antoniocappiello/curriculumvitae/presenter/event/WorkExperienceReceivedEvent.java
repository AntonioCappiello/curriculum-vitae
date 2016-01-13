package com.antoniocappiello.curriculumvitae.presenter.event;

import com.antoniocappiello.curriculumvitae.model.WorkExperience;

import java.util.List;

public class WorkExperienceReceivedEvent {
    private final List<WorkExperience> mWorkExperienceList;

    public WorkExperienceReceivedEvent(List<WorkExperience> workExperienceList) {
        mWorkExperienceList = workExperienceList;
    }

    public List<WorkExperience> getWorkExperienceList() {
        return mWorkExperienceList;
    }
}
