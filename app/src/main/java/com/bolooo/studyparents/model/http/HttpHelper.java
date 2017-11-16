package com.bolooo.studyparents.model.http;

import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.model.http.respone.MyHttpResponse;

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
}
