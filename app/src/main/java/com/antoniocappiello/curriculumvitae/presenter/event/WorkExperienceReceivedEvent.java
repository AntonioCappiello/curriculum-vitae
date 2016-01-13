/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 11:11 AM
 */

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
