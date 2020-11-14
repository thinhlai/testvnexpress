/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: BaseView.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 11:29 PM
 */

package com.thinhlh.testvnexpress.ui.base;

import androidx.annotation.StringRes;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public interface BaseView {
    void showMessage(String message);
    void showMessage(@StringRes int message);
    void showToast(@StringRes int message);
    void showToast(String message);
}
