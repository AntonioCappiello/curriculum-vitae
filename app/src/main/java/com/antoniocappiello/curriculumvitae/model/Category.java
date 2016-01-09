package com.antoniocappiello.curriculumvitae.model;

import com.antoniocappiello.curriculumvitae.R;

public enum Category {
    PERSONAL_INFORMATION(R.string.presona_informations, R.drawable.personal_informations),
    EDUCATION(R.string.education, R.drawable.education),
    WORK_EXPERIENCE(R.string.work_experience, R.drawable.work_experience),
    INTERESTS(R.string.interests, R.drawable.interests),
    LIBRARY(R.string.library, R.drawable.library);

    private final int mTitleResourceId;
    private final int mImageResourceId;

    Category(int titleResourceId, int imageResourceId) {
        mTitleResourceId = titleResourceId;
        mImageResourceId = imageResourceId;
    }

    public int getTitleResourceId() {
        return mTitleResourceId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
