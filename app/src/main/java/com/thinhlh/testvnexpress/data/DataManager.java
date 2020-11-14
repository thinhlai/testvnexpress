/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: DataManager.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:48 PM
 */

package com.thinhlh.testvnexpress.data;

import com.thinhlh.testvnexpress.data.model.RssRoot;

import io.reactivex.Single;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public interface DataManager {
    /**
     * Get news from service
     *
     * @return
     */
    Single<RssRoot> getNews();

    /**
     * Get current theme
     * @return
     */
    int getTheme();

    /**
     * Save theme
     */
    void saveTheme(int theme);
}

