package com.antoniocappiello.curriculumvitae.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.controller.WebApiClientFactory;
import com.antoniocappiello.curriculumvitae.controller.WebApiController;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.google.gson.JsonElement;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CategoryActivity extends AppCompatActivity {

    public static final String CATEGORY = "category";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.image)
    ImageView mImageView;

    private Category mCategory;
    private WebApiController mWebApiController;

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
        if(mWebApiController == null){
            mWebApiController = new WebApiController(new WebApiClientFactory().getClient());
        }
        switch (mCategory){
            case PERSONAL_INFO:
                mWebApiController.readAboutMe();
                break;
            case EDUCATION:
                mWebApiController.readEducation();
                break;
            case WORK_EXPERIENCE:
                mWebApiController.readWorkExperience();
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
}
