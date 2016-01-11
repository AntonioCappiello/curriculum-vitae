package com.antoniocappiello.curriculumvitae.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.antoniocappiello.curriculumvitae.App;
import com.antoniocappiello.curriculumvitae.BuildConfig;
import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.presenter.adapter.CategoryAdapter;
import com.antoniocappiello.curriculumvitae.presenter.event.CategoryClickedEvent;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.web_view)
    WebView mWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.category_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        initNavigationDrawer();
        initWebView();
        initCategoryRecyclerView();

    }

    private void initCategoryRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(Category.values());
        mRecyclerView.setAdapter(categoryAdapter);
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        mDrawerLayout.openDrawer(Gravity.LEFT);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cv) {
            mWebView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_website) {
            mWebView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mWebView.loadUrl(BuildConfig.URL_WEBSITE);
        } else if (id == R.id.nav_github) {
            mWebView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mWebView.loadUrl(BuildConfig.URL_GITHUB);
        } else if (id == R.id.nav_play_store) {
            mWebView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mWebView.loadUrl(BuildConfig.URL_PLAY_STORE);
        } else if (id == R.id.nav_stackoverflow) {
            mWebView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mWebView.loadUrl(BuildConfig.URL_STACKOVERFLOW);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_download) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

    public void onEvent(CategoryClickedEvent event){
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(CategoryActivity.CATEGORY, event.getCategory());

        Pair<View, String> pairImage = Pair.create((View) event.getImageView(), "category_transition");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairImage);

        startActivity(intent, options.toBundle());
    }
}
