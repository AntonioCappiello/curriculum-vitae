package com.antoniocappiello.curriculumvitae.presenter.tracker;


import com.antoniocappiello.curriculumvitae.BuildConfig;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;


public class FabricTracker {

    public enum Goal {
        CV_DOWNLOADED,
        CV_SHARED,
        WEBSITE_VISITED,
        GITHUB_PROFILE_VISITED,
        PLAY_STORE_VISITED,
        STACKOVERFLOW_VISITED,
        CATEGORY_ABOUT_ME,
        CATEGORY_EDUCATION,
        CATEGORY_WORK_EXPERIENCE,
        CATEGORY_INTERESTS,
        CATEGORY_LIBRARY
    }

    public static void log(Goal goal) {
        if (BuildConfig.USE_CRASHLYTICS) {
            Answers.getInstance()
                    .logCustom(new CustomEvent(goal.name()));
        }
    }
}