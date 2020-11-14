/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: NewsDetailActivity.java
 * Created: 2020/11/14
 * Last modified: 11/14/20 12:08 PM
 */

package com.thinhlh.testvnexpress.ui.news_detail;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.thinhlh.testvnexpress.databinding.ActivityNewsDetailBinding;
import com.thinhlh.testvnexpress.di.component.ActivityComponent;
import com.thinhlh.testvnexpress.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Thinh Lai on 11/14/20.
 */
public class NewsDetailActivity extends BaseActivity<ActivityNewsDetailBinding> implements NewsDetailView {

    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_LINK = "extra_link";

    public static void start(Context context, String title, String link) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_LINK, link);
        context.startActivity(intent);
    }

    @Inject
    NewsDetailPresenter mPresenter;

    private String mTitle;
    private String mLink;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected ActivityNewsDetailBinding getViewBinding() {
        return ActivityNewsDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        mViewBinding.back.setOnClickListener(view -> {
            finish();
        });
        AppCompatDelegate.setDefaultNightMode(mPresenter.getTheme());
        WebSettings webSettings = mViewBinding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mViewBinding.webview.setWebChromeClient(new WebChromeClient());

        if (mPresenter.getTheme() == AppCompatDelegate.MODE_NIGHT_YES) {
            // dark theme
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setForceDark(webSettings, WebSettingsCompat.FORCE_DARK_ON);
            }

        }
    }

    @Override
    protected void initData() {
        mTitle = getIntent().getExtras().getString(EXTRA_TITLE, "");
        mLink = getIntent().getExtras().getString(EXTRA_LINK, "");
        mViewBinding.title.setText(mTitle);
        mViewBinding.webview.loadUrl(mLink);
    }

    @Override
    protected void attachPresenter() {
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
    }
}
