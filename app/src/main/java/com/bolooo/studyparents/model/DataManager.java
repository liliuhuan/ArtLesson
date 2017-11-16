package com.bolooo.studyparents.model;

import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.model.db.DBHelper;
import com.bolooo.studyparents.model.http.HttpHelper;
import com.bolooo.studyparents.model.http.respone.MyHttpResponse;
import com.bolooo.studyparents.model.pfres.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class DataManager implements DBHelper,HttpHelper,PreferencesHelper {
    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public boolean isFrist() {
        return mPreferencesHelper.isFrist();
    }

    @Override
    public void setIsFrist(boolean isFrist) {
        mPreferencesHelper.setIsFrist(isFrist);
    }

    @Override
    public String getToken() {
        return mPreferencesHelper.getToken();
    }

    @Override
    public void saveToken(String token) {
        mPreferencesHelper.saveToken(token);
    }

    @Override
    public void saveCityData(String s) {
        mPreferencesHelper.saveCityData(s);
    }

    @Override
    public String getCityData() {
        return mPreferencesHelper.getCityData();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public Flowable<MyHttpResponse<SplashEntity>> fetchSplashInfo(String key) {
        return mHttpHelper.fetchSplashInfo(key);
    }

    @Override
    public Flowable<MyHttpResponse<String>> fetchCityInfo(String params) {
        return mHttpHelper.fetchCityInfo(params);
    }
}
