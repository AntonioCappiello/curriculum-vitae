package com.antoniocappiello.curriculumvitae;

import android.app.Application;
import android.test.ApplicationTestCase;

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