/*
 * Copyright (c) 2019. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh1
 * File: ReleaseTree.java
 * Created: 2019/10/17
 * Last modified: 10/17/19 3:38 PM
 */

package com.thinhlh.testvnexpress.utils.timber;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {

    @Override
    protected boolean isLoggable(@Nullable String tag, int priority) {
        return priority >= Log.INFO;
    }

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {

    }
}
