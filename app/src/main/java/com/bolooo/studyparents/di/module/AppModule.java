package com.bolooo.studyparents.di.module;

import com.bolooo.studyparents.app.App;
import com.bolooo.studyparents.model.DataManager;
import com.bolooo.studyparents.model.db.DBHelper;
import com.bolooo.studyparents.model.db.RealmHelper;
import com.bolooo.studyparents.model.http.HttpHelper;
import com.bolooo.studyparents.model.http.RetrofitHelper;
import com.bolooo.studyparents.model.pfres.ImplPreferencesHelper;
import com.bolooo.studyparents.model.pfres.PreferencesHelper;

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
