/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 11:26 PM
 */

package com.antoniocappiello.curriculumvitae.espresso;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.TestApp;
import com.antoniocappiello.curriculumvitae.model.AboutMe;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.antoniocappiello.curriculumvitae.view.CategoryActivity;
import com.antoniocappiello.curriculumvitae.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CategoryActivityPersonalInfoTest {

    @Rule
    public ActivityTestRule<CategoryActivity> activityTestRule = new ActivityTestRule<CategoryActivity>(CategoryActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, MainActivity.class);
            result.putExtra(CategoryActivity.CATEGORY, Category.PERSONAL_INFO);
            return result;
        }
    };

    @Inject
    WebApiService mWebApiService;

    @Before
    public void setUp() {
        ((TestApp) activityTestRule.getActivity().getApplication()).appComponent().inject(this);
    }

    @Test
    public void correctNameIsDisplayed() {
        AboutMe aboutMe = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.full_name_value)).check(matches(withText(aboutMe.getName())));
    }

    @Test
    public void correctNationalityIsDisplayed() {
        AboutMe aboutMe = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.nationality_value)).check(matches(withText(aboutMe.getNationality())));
    }

    @Test
    public void correctOverviewIsDisplayed() {
        AboutMe aboutMe = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.overview_value)).check(matches(withText(aboutMe.getOverview())));
    }

    @Test
    public void correctCarrerIsDisplayed() {
        AboutMe aboutMe = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.career_value)).check(matches(withText(aboutMe.getCareer())));
    }

    @Test
    public void correctFreeTimeIsDisplayed() {
        AboutMe aboutMe = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.free_time_value)).check(matches(withText(aboutMe.getFreeTime())));
    }

    @Test
    public void gifIsDisplayedAfterContentIsLoaded(){
        mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.gif_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}