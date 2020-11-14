package com.thinhlh.testvnexpress.di.module;

import android.app.Application;
import android.content.Context;

import com.thinhlh.testvnexpress.data.DataManager;
import com.thinhlh.testvnexpress.data.DataManagerHelper;
import com.thinhlh.testvnexpress.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, DataModule.class})
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerHelper dataManagerHelper) {
        return dataManagerHelper;
    }
}
