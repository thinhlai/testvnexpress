/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: PrefHelper.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:52 PM
 */

package com.thinhlh.testvnexpress.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Thinh Lai on 11/13/20.
 */
@Singleton
public class PrefHelper implements Pref {

    /**
     * Preference file name
     */
    private static final String PREF_FILE_NAME = "vnexpress_pref";

    private static final String PREF_KEY_THEME_MODE = "pref_key_theme_mode";

    private final SharedPreferences mPref;

    @Inject
    public PrefHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void clear() {
        mPref.edit().clear().apply();
    }

    @Override
    public void saveThemeMode(int theme) {
        mPref.edit().putInt(PREF_KEY_THEME_MODE, theme).apply();
    }

    @Override
    public int getThemeMode() {
        return mPref.getInt(PREF_KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_NO);
    }
}
