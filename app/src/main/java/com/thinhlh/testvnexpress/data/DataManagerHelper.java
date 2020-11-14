/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: DataManagerHelper.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:50 PM
 */

package com.thinhlh.testvnexpress.data;

import com.thinhlh.testvnexpress.data.model.RssRoot;
import com.thinhlh.testvnexpress.data.pref.Pref;
import com.thinhlh.testvnexpress.data.service.VNExpressService;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public class DataManagerHelper implements DataManager {

    /**
     * Share preferences
     */
    private Pref mPref;

    /**
     * VNExpressService
     * get data from RSS URL
     */
    private VNExpressService mService;

    @Inject
    public DataManagerHelper(VNExpressService vnExpressService, Pref pref) {
        this.mService = vnExpressService;
        this.mPref = pref;
    }

    @Override
    public Single<RssRoot> getNews() {
        return mService.getNews();
    }

    @Override
    public int getTheme() {
        return mPref.getThemeMode();
    }

    @Override
    public void saveTheme(int theme) {
        mPref.saveThemeMode(theme);
    }
}

