package com.bolooo.artlesson.model.pfres;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public interface PreferencesHelper {
    //判断是否第一次登陆
    boolean isFrist();
    void setIsFrist(boolean isFrist);
    //存取token
    String getToken();

    void saveToken(String token);
    //存取city信息
    void saveCityData(String s);
    String getCityData();

    //获取图片状态，是否缓存
    boolean getNoImageState();

    void setNoImageState(boolean state);

    //获取广告版本哈
    int getAdNumber();
    void setAdNumber(int number);
}
