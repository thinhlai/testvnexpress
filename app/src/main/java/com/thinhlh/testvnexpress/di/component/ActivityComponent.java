package com.thinhlh.testvnexpress.di.component;

import com.thinhlh.testvnexpress.di.PerActivity;
import com.thinhlh.testvnexpress.di.module.ActivityModule;
import com.thinhlh.testvnexpress.ui.news_detail.NewsDetailActivity;
import com.thinhlh.testvnexpress.ui.news_list.NewsListActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(NewsListActivity newsListActivity);
    void inject(NewsDetailActivity newsDetailActivity);
}
