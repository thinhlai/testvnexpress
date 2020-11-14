package com.thinhlh.testvnexpress.di.module;

import android.content.Context;

import com.thinhlh.testvnexpress.data.pref.Pref;
import com.thinhlh.testvnexpress.data.pref.PrefHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataModule {

    private final Context context;

    public DataModule(final Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Pref providePref() {
        return new PrefHelper(context);
    }

}
