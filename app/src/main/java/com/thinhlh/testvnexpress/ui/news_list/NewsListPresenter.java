/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: NewsListPresenter.java
 * Created: 2020/11/14
 * Last modified: 11/14/20 12:15 AM
 */

package com.thinhlh.testvnexpress.ui.news_list;

import com.thinhlh.testvnexpress.data.DataManager;
import com.thinhlh.testvnexpress.di.ConfigPersistent;
import com.thinhlh.testvnexpress.ui.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Thinh Lai on 11/14/20.
 */
@ConfigPersistent
public class NewsListPresenter extends BasePresenter<NewsListView> {

    @Inject
    public NewsListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    int getTheme() {
        return mDataManager.getTheme();
    }

    void saveTheme(int theme) {
        mDataManager.saveTheme(theme);
    }

    void getNews() {
        getView().showLoading();
        Disposable disposable = mDataManager.getNews()
                .subscribeOn(Schedulers.io())
                .filter(rssRoot -> {
                    if (rssRoot != null) {
                        rssRoot.parseDescription();
                    }
                    return true;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                })
                .subscribe(
                        response -> {
                            if (!isViewAttached()) return;
                            if (response != null && response.channel != null && response.channel.item != null) {
                                getView().showNewsList(response.channel.item);
                            } else {
                                getView().showNewsList(new ArrayList<>());
                            }
                        },
                        throwable -> {
                            Timber.e(throwable, "getNews error");
                            if (!isViewAttached()) return;
                            getView().showToast(throwable.getMessage());
                        });
        addDisposable(disposable);
    }
}
