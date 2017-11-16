package com.bolooo.studyparents.di.compant;

import android.app.Activity;

import com.bolooo.studyparents.MainActivity;
import com.bolooo.studyparents.di.module.ActivityModule;
import com.bolooo.studyparents.di.scope.ActivityScope;
import com.bolooo.studyparents.ui.SplashActivity;

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
