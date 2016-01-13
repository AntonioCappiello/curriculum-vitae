/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 2:28 AM
 */

package com.antoniocappiello.curriculumvitae.model;

import com.antoniocappiello.curriculumvitae.R;

public enum Category {
    PERSONAL_INFO(R.string.personal_info, R.drawable.personal_info, R.layout.about_me),
    EDUCATION(R.string.education, R.drawable.education, R.layout.education),
    WORK_EXPERIENCE(R.string.work_experience, R.drawable.work_experience, R.layout.experience),
    INTERESTS(R.string.interests, R.drawable.interests, R.layout.interests),
    LIBRARY(R.string.library, R.drawable.library, R.layout.library);

    private final int mTitleResourceId;
    private final int mImageResourceId;
    private final int mLayoutResourceId;

    Category(int titleResourceId, int imageResourceId, int layoutResourceId) {
        mTitleResourceId = titleResourceId;
        mImageResourceId = imageResourceId;
        mLayoutResourceId = layoutResourceId;
    }

    public int getTitleResourceId() {
        return mTitleResourceId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getLayoutResourceId() {
        return mLayoutResourceId;
    }
}
