/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 11:26 PM
 */

package com.antoniocappiello.curriculumvitae;

import com.antoniocappiello.curriculumvitae.injector.DaggerTestAppComponent;
import com.antoniocappiello.curriculumvitae.injector.TestAppComponent;
import com.antoniocappiello.curriculumvitae.injector.TestAppModule;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TestApp extends App {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    @Override
    public TestAppComponent appComponent() {
        return testAppComponent;
    }
}