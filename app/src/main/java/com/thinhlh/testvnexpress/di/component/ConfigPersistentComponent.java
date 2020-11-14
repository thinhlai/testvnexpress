package com.thinhlh.testvnexpress.di.component;

import com.thinhlh.testvnexpress.di.ConfigPersistent;
import com.thinhlh.testvnexpress.di.module.ActivityModule;
import com.thinhlh.testvnexpress.di.module.FragmentModule;

import dagger.Component;


@ConfigPersistent
@Component(dependencies = AppComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);
}
