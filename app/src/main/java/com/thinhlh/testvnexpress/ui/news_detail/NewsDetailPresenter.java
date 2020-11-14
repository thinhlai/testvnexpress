/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: NewsDetailPresenter.java
 * Created: 2020/11/14
 * Last modified: 11/14/20 12:15 AM
 */

package com.thinhlh.testvnexpress.ui.news_detail;

import com.thinhlh.testvnexpress.data.DataManager;
import com.thinhlh.testvnexpress.di.ConfigPersistent;
import com.thinhlh.testvnexpress.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Thinh Lai on 11/14/20.
 */
@ConfigPersistent
public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    @Inject
    public NewsDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    int getTheme() {
        return mDataManager.getTheme();
    }
}
