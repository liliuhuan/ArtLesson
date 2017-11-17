package com.bolooo.artlesson.model.http;

import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.entity.SplashEntity;
import com.bolooo.artlesson.model.http.respone.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public interface HttpHelper {
    Flowable<MyHttpResponse<SplashEntity>> fetchSplashInfo(String key);
    Flowable<MyHttpResponse<String>> fetchCityInfo(String params);
    Flowable<MyHttpResponse<AdEntity>> fetchMainAdInfo();
    Flowable<MyHttpResponse<AdEntity>> fetchMainBannerInfo(int type);
    Flowable<MyHttpResponse<HomeDataEntity>> fetchMainCourseListInfo(int type, Map<String, String> params);
}
