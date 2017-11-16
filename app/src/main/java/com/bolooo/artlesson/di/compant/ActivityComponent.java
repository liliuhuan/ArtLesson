package com.bolooo.artlesson.di.compant;

import android.app.Activity;

import com.bolooo.artlesson.MainActivity;
import com.bolooo.artlesson.di.module.ActivityModule;
import com.bolooo.artlesson.di.scope.ActivityScope;
import com.bolooo.artlesson.ui.SplashActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity welcomeActivity);

    void inject(MainActivity mainActivity);
//
//    void inject(ZhihuDetailActivity zhihuDetailActivity);
//
//    void inject(ThemeActivity themeActivity);
//
//    void inject(SectionActivity sectionActivity);
//
//    void inject(RepliesActivity repliesActivity);
//
//    void inject(NodeListActivity nodeListActivity);
}
