/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: VnexpressApp.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 9:35 PM
 */

package com.thinhlh.testvnexpress;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.thinhlh.testvnexpress.di.component.AppComponent;
import com.thinhlh.testvnexpress.di.component.DaggerAppComponent;
import com.thinhlh.testvnexpress.di.module.AppModule;
import com.thinhlh.testvnexpress.di.module.DataModule;
import com.thinhlh.testvnexpress.di.module.NetworkModule;
import com.thinhlh.testvnexpress.utils.timber.ReleaseTree;

import timber.log.Timber;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public class VnexpressApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    public static VnexpressApp get(Context context) {
        return (VnexpressApp) context.getApplicationContext();
    }


    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this, BuildConfig.API_URL))
                    .dataModule(new DataModule(this))
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

}
