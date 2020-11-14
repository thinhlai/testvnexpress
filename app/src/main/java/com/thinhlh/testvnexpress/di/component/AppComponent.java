package com.thinhlh.testvnexpress.di.component;

import android.app.Application;
import android.content.Context;

import com.thinhlh.testvnexpress.data.DataManager;
import com.thinhlh.testvnexpress.di.ApplicationContext;
import com.thinhlh.testvnexpress.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}
