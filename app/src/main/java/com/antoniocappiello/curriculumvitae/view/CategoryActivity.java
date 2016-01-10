package com.antoniocappiello.curriculumvitae.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.model.Education;
import com.antoniocappiello.curriculumvitae.model.WorkExperience;
import com.antoniocappiello.curriculumvitae.presenter.DateUtils;
import com.antoniocappiello.curriculumvitae.presenter.event.AboutMeReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.EducationReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.WorkExperienceReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiClientFactory;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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

    private Category mCategory;
    private WebApiService mWebApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        mCategory = (Category) getIntent().getExtras().get(CATEGORY);

        setSupportActionBar(mToolbar);
        setBackNavigation();
        setDataInToolbar(mCategory);

        loadContent();

    }

    private void loadContent() {
        if(mWebApiService == null){
            mWebApiService = new WebApiService(new WebApiClientFactory().getClient());
        }
        switch (mCategory){
            case PERSONAL_INFO:
                mWebApiService.readAboutMe();
                break;
            case EDUCATION:
                mWebApiService.readEducation();

                break;
            case WORK_EXPERIENCE:
                mWebApiService.readWorkExperience();
                break;
        }
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

    public void onEvent(AboutMeReceivedEvent event){
        Logger.d(event.getAboutMe().toString());
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
        for(WorkExperience workExperience: event.getWorkExperienceList()){
            Logger.d(workExperience.toString());
        }
    }
}
