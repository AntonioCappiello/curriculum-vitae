package com.antoniocappiello.curriculumvitae.presenter.event;

import com.antoniocappiello.curriculumvitae.model.AboutMe;

public class AboutMeReceivedEvent {
    private final AboutMe mAboutMe;

    public AboutMeReceivedEvent(AboutMe aboutMe) {
        mAboutMe = aboutMe;
    }

    public AboutMe getAboutMe() {
        return mAboutMe;
    }
}
