package com.bolooo.artlesson.di.compant;


import com.bolooo.artlesson.app.App;
import com.bolooo.artlesson.di.module.AppModule;
import com.bolooo.artlesson.di.module.HttpModule;
import com.bolooo.artlesson.model.DataManager;
import com.bolooo.artlesson.model.db.RealmHelper;
import com.bolooo.artlesson.model.http.RetrofitHelper;
import com.bolooo.artlesson.model.pfres.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
