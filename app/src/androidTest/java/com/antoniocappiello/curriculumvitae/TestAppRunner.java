package com.antoniocappiello.curriculumvitae;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class TestAppRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = TestApp.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}