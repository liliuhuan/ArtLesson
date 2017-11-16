package com.bolooo.studyparents.model.http;

import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.model.http.api.MyApis;
import com.bolooo.studyparents.model.http.respone.MyHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class RetrofitHelper implements HttpHelper {

    private MyApis mMyApiService;
    @Inject
    public RetrofitHelper(MyApis myApiService) {
        this.mMyApiService = myApiService;
    }


    @Override
    public Flowable<MyHttpResponse<SplashEntity>> fetchSplashInfo(String key) {
        return mMyApiService.getSplashInfo(key);
    }

    @Override
    public Flowable<MyHttpResponse<String>> fetchCityInfo(String params) {
        return mMyApiService.getCityAreas(params);
    }
}
