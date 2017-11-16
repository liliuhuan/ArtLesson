package com.bolooo.artlesson.model.pfres;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public interface PreferencesHelper {
    boolean isFrist();
    void setIsFrist(boolean isFrist);
    String getToken();

    void saveToken(String token);

    void saveCityData(String s);
    String getCityData();

    boolean getNoImageState();

    void setNoImageState(boolean state);
}
