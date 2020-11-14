/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: BaseActivity.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 11:29 PM
 */

package com.thinhlh.testvnexpress.ui.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.LongSparseArray;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.thinhlh.testvnexpress.VnexpressApp;
import com.thinhlh.testvnexpress.di.component.ActivityComponent;
import com.thinhlh.testvnexpress.di.component.ConfigPersistentComponent;
import com.thinhlh.testvnexpress.di.component.DaggerConfigPersistentComponent;
import com.thinhlh.testvnexpress.di.module.ActivityModule;

import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity implements BaseView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> componentsArray =
            new LongSparseArray<>();
    private long activityId;

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract VB getViewBinding();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    protected VB mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewBinding();
        setContentView(mViewBinding.getRoot());
        activityId =
                savedInstanceState != null
                        ? savedInstanceState.getLong(KEY_ACTIVITY_ID)
                        : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (componentsArray.get(activityId) == null) {
            Timber.d("Creating new ConfigPersistentComponent id=%d", activityId);
            configPersistentComponent =
                    DaggerConfigPersistentComponent.builder()
                            .appComponent(VnexpressApp.get(this).getComponent())
                            .build();
            componentsArray.put(activityId, configPersistentComponent);
        } else {
            Timber.d("Reusing ConfigPersistentComponent id=%d", activityId);
            configPersistentComponent = componentsArray.get(activityId);
        }
        ActivityComponent activityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachPresenter();
        initView();
        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.d("Clearing ConfigPersistentComponent id=%d", activityId);
            componentsArray.remove(activityId);
        }
        detachPresenter();
        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void showMessage(int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
