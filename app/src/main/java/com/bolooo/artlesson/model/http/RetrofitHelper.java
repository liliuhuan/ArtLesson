package com.bolooo.artlesson.model.http;

import android.util.Log;

import com.bolooo.artlesson.entity.SplashEntity;
import com.bolooo.artlesson.model.http.api.MyApis;
import com.bolooo.artlesson.model.http.respone.MyHttpResponse;

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
        Flowable<MyHttpResponse<SplashEntity>> splashInfo = mMyApiService.getSplashInfo(key);
        Log.d("tag==",splashInfo.toString());
        return mMyApiService.getSplashInfo(key);
    }

    @Override
    public Flowable<MyHttpResponse<String>> fetchCityInfo(String params) {
        return mMyApiService.getCityAreas(params);
    }
}
