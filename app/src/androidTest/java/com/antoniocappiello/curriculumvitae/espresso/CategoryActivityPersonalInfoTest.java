package com.antoniocappiello.curriculumvitae.espresso;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.antoniocappiello.curriculumvitae.App;
import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.antoniocappiello.curriculumvitae.view.CategoryActivity;
import com.antoniocappiello.curriculumvitae.view.MainActivity;
import com.google.gson.JsonElement;
import com.orhanobut.logger.Logger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
        mWebApiService = ((App) activityTestRule.getActivity().getApplication())
                .appComponent()
                .webApiService();
    }

    @Test
    public void correctWeatherDataDisplayed() {
        JsonElement data = mWebApiService.readAboutMe().toBlocking().first();
        onView(withId(R.id.content_text_view)).check(matches(withText(data.toString())));
    }
}