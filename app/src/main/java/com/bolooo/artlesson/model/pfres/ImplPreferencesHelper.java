package com.bolooo.artlesson.model.pfres;

import android.content.Context;
import android.content.SharedPreferences;

import com.bolooo.artlesson.app.App;

import javax.inject.Inject;

import static com.bolooo.artlesson.model.http.Constants.CITY_DATA;
import static com.bolooo.artlesson.model.http.Constants.TOKEN;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class ImplPreferencesHelper implements PreferencesHelper {
    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public boolean isFrist() {
        return mSPrefs.getBoolean("isFrist",false);
    }

    @Override
    public void setIsFrist(boolean isFrist) {
        mSPrefs.edit().putBoolean("isFrist",isFrist).apply();
    }

    @Override
    public String getToken() {
        return mSPrefs.getString(TOKEN,"");
    }

    @Override
    public void saveToken(String token) {
        mSPrefs.edit().putString(TOKEN,token).apply();
    }

    @Override
    public void saveCityData(String s) {
        mSPrefs.edit().putString(CITY_DATA,s).apply();
    }

    @Override
    public String getCityData() {
        return  mSPrefs.getString(CITY_DATA, "");
    }

    @Override
    public boolean getNoImageState() {
        return mSPrefs.getBoolean("image_cache",false);
    }

    @Override
    public void setNoImageState(boolean state) {
        mSPrefs.edit().putBoolean("image_cache",state).apply();
    }
}
