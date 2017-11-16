package com.bolooo.artlesson.di.module;

import com.bolooo.artlesson.app.App;
import com.bolooo.artlesson.model.DataManager;
import com.bolooo.artlesson.model.db.DBHelper;
import com.bolooo.artlesson.model.db.RealmHelper;
import com.bolooo.artlesson.model.http.HttpHelper;
import com.bolooo.artlesson.model.http.RetrofitHelper;
import com.bolooo.artlesson.model.pfres.ImplPreferencesHelper;
import com.bolooo.artlesson.model.pfres.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
