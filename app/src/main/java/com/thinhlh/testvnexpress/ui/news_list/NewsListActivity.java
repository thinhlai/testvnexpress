/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: NewsListActivity.java
 * Created: 2020/11/14
 * Last modified: 11/14/20 12:10 AM
 */

package com.thinhlh.testvnexpress.ui.news_list;

import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.thinhlh.testvnexpress.data.model.RssItem;
import com.thinhlh.testvnexpress.databinding.ActivityNewsListBinding;
import com.thinhlh.testvnexpress.di.component.ActivityComponent;
import com.thinhlh.testvnexpress.ui.base.BaseActivity;
import com.thinhlh.testvnexpress.ui.news_detail.NewsDetailActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Thinh Lai on 11/14/20.
 */
public class NewsListActivity extends BaseActivity<ActivityNewsListBinding> implements NewsListView, SwipeRefreshLayout.OnRefreshListener, NewsListAdapter.NewsListAdapterListener {

    @Inject
    NewsListPresenter mPresenter;

    @Inject
    NewsListAdapter mAdapter;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected ActivityNewsListBinding getViewBinding() {
        return ActivityNewsListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void attachPresenter() {
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
    }

    @Override
    protected void initView() {
        mViewBinding.swipeRefresh.setOnRefreshListener(this);
        mViewBinding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.rvNews.setHasFixedSize(true);
        mAdapter.setListener(this);
        mViewBinding.rvNews.setAdapter(mAdapter);
        mViewBinding.switchTheme.setChecked(mPresenter.getTheme() == AppCompatDelegate.MODE_NIGHT_YES ? true : false);
        AppCompatDelegate.setDefaultNightMode(mPresenter.getTheme());
        mViewBinding.switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                mPresenter.saveTheme(b ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getNews();
    }

    @Override
    public void onRefresh() {
        mPresenter.getNews();
    }

    @Override
    public void showLoading() {
        mViewBinding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mViewBinding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showNewsList(List<RssItem> list) {
        mAdapter.setItems(list);
    }

    @Override
    public void onClickItem(RssItem item) {
        NewsDetailActivity.start(this, item.title, item.link);
    }
}
