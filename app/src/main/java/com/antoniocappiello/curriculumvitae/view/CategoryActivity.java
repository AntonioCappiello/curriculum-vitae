package com.antoniocappiello.curriculumvitae.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.model.Education;
import com.antoniocappiello.curriculumvitae.model.WorkExperience;
import com.antoniocappiello.curriculumvitae.presenter.DateUtils;
import com.antoniocappiello.curriculumvitae.presenter.adapter.BookAdapter;
import com.antoniocappiello.curriculumvitae.presenter.adapter.WrappingLinearLayoutManager;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookReader;
import com.antoniocappiello.curriculumvitae.presenter.event.AboutMeReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.EducationReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.event.WorkExperienceReceivedEvent;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

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

    @Bind(R.id.category_content_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.gif_view)
    GifView mGifView;

    @Bind(R.id.content_text_view)
    TextView mContentTextView;

    @Inject
    WebApi mWebApi;

    @Inject
    WebApiService mWebApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        inject();
        setSupportActionBar(mToolbar);
        setBackNavigation();
        
        Category category = (Category) getIntent().getExtras().get(CATEGORY);
        setDataInToolbar(category);
        loadContent(category);
    }

    private void inject() {
        ((App)getApplication()).appComponent().inject(this);
    }

    private void loadContent(Category category) {
        switch (category){
            case PERSONAL_INFO:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readAboutMe();
                mContentTextView.setVisibility(View.VISIBLE);
                mGifView.setVisibility(View.VISIBLE);
                mGifView.setMovieResource(R.mipmap.typing);
                break;
            case EDUCATION:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readEducation();
                break;
            case WORK_EXPERIENCE:
                mRecyclerView.setVisibility(View.GONE);
                mWebApiService.readWorkExperience();
                break;
            case LIBRARY:
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setHasFixedSize(true);
                WrappingLinearLayoutManager layoutManager = new WrappingLinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);

                BookAdapter bookAdapter = new BookAdapter(new BookReader().read());
                mRecyclerView.setAdapter(bookAdapter);
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
        mContentTextView.setText(event.getAboutMe().toString());
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
