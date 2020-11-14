/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: Pref.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:52 PM
 */

package com.thinhlh.testvnexpress.data.pref;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public interface Pref {
    /**
     * Clear all data on share preference
     */
    void clear();

    /**
     * Save current theme
     *
     * @param theme
     */
    void saveThemeMode(int theme);

    /**
     * Get current theme
     *
     * @return
     */
    int getThemeMode();
}
