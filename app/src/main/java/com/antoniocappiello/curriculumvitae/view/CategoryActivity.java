/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 9:24 PM
 */

package com.antoniocappiello.curriculumvitae.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antoniocappiello.curriculumvitae.App;
import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.AboutMe;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.model.Education;
import com.antoniocappiello.curriculumvitae.model.WorkExperience;
import com.antoniocappiello.curriculumvitae.presenter.DateUtils;
import com.antoniocappiello.curriculumvitae.presenter.adapter.BookAdapter;
import com.antoniocappiello.curriculumvitae.presenter.adapter.WrappingLinearLayoutManager;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookReader;
import com.antoniocappiello.curriculumvitae.presenter.event.EducationReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.WorkExperienceReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.tracker.FabricTracker;
import com.antoniocappiello.curriculumvitae.presenter.tracker.FabricTracker.Goal;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoryActivity extends AppCompatActivity {

    public static final String CATEGORY = "category";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.image)
    ImageView mImageView;

    @Bind(R.id.category_content_root)
    LinearLayout mCategoryContentRoot;

    @Bind(R.id.category_content_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.gif_view)
    GifView mGifView;

    @Bind(R.id.category_content_first_child)
    LinearLayout mCategoryContentFirstChild;

    @Inject
    WebApi mWebApi;

    @Inject
    WebApiService mWebApiService;

    @Inject
    Tracker gaTracker;

    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        inject();
        setSupportActionBar(mToolbar);
        setBackNavigation();
        
        mCategory = (Category) getIntent().getExtras().get(CATEGORY);
        setDataInToolbar(mCategory);
        loadContent(mCategory);
    }

    private void inject() {
        ((App)getApplication()).appComponent().inject(this);
    }

    private void loadContent(final Category category) {
        switch (category){
            case PERSONAL_INFO:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readAboutMe()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getReadAboutMeSubscriber());
                FabricTracker.log(Goal.CATEGORY_ABOUT_ME);
                break;
            case EDUCATION:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readEducation();
                FabricTracker.log(Goal.CATEGORY_EDUCATION);
                break;
            case WORK_EXPERIENCE:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readWorkExperience();
                FabricTracker.log(Goal.CATEGORY_WORK_EXPERIENCE);
                break;
            case INTERESTS:
                FabricTracker.log(Goal.CATEGORY_INTERESTS);
                break;
            case LIBRARY:
                FabricTracker.log(Goal.CATEGORY_LIBRARY);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setHasFixedSize(true);
                WrappingLinearLayoutManager layoutManager = new WrappingLinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);

                BookAdapter bookAdapter = new BookAdapter(new BookReader().readAll());
                mRecyclerView.setAdapter(bookAdapter);
                break;
        }
    }

    private Subscriber<? super AboutMe> getReadAboutMeSubscriber() {
        return new Subscriber<AboutMe>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                showSnackbar();
            }

            @Override
            public void onNext(AboutMe aboutMe) {
                setData(aboutMe);
            }
        };
    }

    private void showSnackbar() {
        Snackbar.make(mCategoryContentRoot, getResources().getText(R.string.error_web_api_query_failed), Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadContent(mCategory);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(CategoryActivity.this, R.color.primary_dark))
                .show();
    }

    private void setData(AboutMe aboutMe) {
        mCategoryContentFirstChild.setVisibility(View.VISIBLE);
        mGifView.setVisibility(View.VISIBLE);
        mGifView.setMovieResource(R.mipmap.typing);

        View aboutMeView = getLayoutInflater().inflate(R.layout.about_me, null);
        mCategoryContentFirstChild.addView(aboutMeView);

        ((TextView) aboutMeView.findViewById(R.id.full_name_value))
                .setText(aboutMe.getName());

        ((TextView) aboutMeView.findViewById(R.id.nationality_value))
                .setText(aboutMe.getNationality());

        ((TextView) aboutMeView.findViewById(R.id.overview_value))
                .setText(aboutMe.getOverview());

        ((TextView) aboutMeView.findViewById(R.id.career_value))
                .setText(aboutMe.getCareer());

        ((TextView) aboutMeView.findViewById(R.id.free_time_value))
                .setText(aboutMe.getFreeTime());

    }


    private void setBackNavigation() {
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp, null));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDataInToolbar(Category category) {
        mImageView.setImageResource(category.getImageResourceId());
        mCollapsingToolbarLayout.setTitle(getResources().getString(category.getTitleResourceId()));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gaTracker.setScreenName(this.getLocalClassName() + " (" + mCategory.name() +")");
        gaTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void onEvent(EducationReceivedEvent event){
        List<Education> educationList = event.getEducationList();
        Collections.sort(educationList);

        Iterator<Education> it = educationList.iterator();
        while (it.hasNext()){
            Education education = it.next();
            TimelineStepView stepView = new TimelineStepView.Builder(this)
                    .setTitle(education.getName())
                    .setSubtitle(education.getProvider())
                    .setDate(DateUtils.yearFromDate(education.getDate()))
                    .setLogo(education.getLogoUrl())
                    .showLine(it.hasNext())
                    .build();
            mCategoryContentRoot.addView(stepView);
        }
    }


    public void onEvent(WorkExperienceReceivedEvent event){
        List<WorkExperience> workExperienceList = event.getWorkExperienceList();
        Collections.sort(workExperienceList);

        Iterator<WorkExperience> it = workExperienceList.iterator();
        while (it.hasNext()){
            WorkExperience workExperience = it.next();

            String dateString = DateUtils.monthAndYearFromDate(workExperience.getDateStart())
                    + "\n"
                    + DateUtils.monthAndYearFromDate(workExperience.getDateEnd());

            TimelineStepView stepView = new TimelineStepView.Builder(this)
                    .setTitle(workExperience.getRole())
                    .setSubtitle(workExperience.getCompany())
                    .setDate(dateString)
                    .setLogo(workExperience.getLogoUrl())
                    .showLine(it.hasNext())
                    .build();
            mCategoryContentRoot.addView(stepView);
        }
    }

}
