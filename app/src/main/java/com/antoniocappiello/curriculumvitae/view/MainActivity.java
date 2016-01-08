package com.antoniocappiello.curriculumvitae.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.antoniocappiello.curriculumvitae.BuildConfig;
import com.antoniocappiello.curriculumvitae.R;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        initNavigationDrawer();
        initWebView();

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
        } else if (id == R.id.nav_website) {
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(BuildConfig.URL_WEBSITE);
        } else if (id == R.id.nav_github) {
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(BuildConfig.URL_GITHUB);
        } else if (id == R.id.nav_play_store) {
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(BuildConfig.URL_PLAY_STORE);
        } else if (id == R.id.nav_stackoverflow) {
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(BuildConfig.URL_STACKOVERFLOW);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_download) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
