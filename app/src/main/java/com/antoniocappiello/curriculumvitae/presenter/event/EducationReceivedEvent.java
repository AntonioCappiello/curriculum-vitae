/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 10:50 AM
 */

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
