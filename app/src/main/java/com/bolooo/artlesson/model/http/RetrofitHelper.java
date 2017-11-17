package com.bolooo.artlesson.model.http;

import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.entity.SplashEntity;
import com.bolooo.artlesson.model.http.api.MyApis;
import com.bolooo.artlesson.model.http.respone.MyHttpResponse;

import java.util.Map;

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
        return mMyApiService.getSplashInfo(key);
    }

    @Override
    public Flowable<MyHttpResponse<String>> fetchCityInfo(String params) {
        return mMyApiService.getCityAreas(params);
    }

    @Override
    public Flowable<MyHttpResponse<AdEntity>> fetchMainAdInfo() {
        return mMyApiService.getMainAd();
    }

    @Override
    public Flowable<MyHttpResponse<AdEntity>> fetchMainBannerInfo(int type) {
        return mMyApiService.getBannerListInfo(type);
    }

    @Override
    public Flowable<MyHttpResponse<HomeDataEntity>> fetchMainCourseListInfo(int type, Map<String, String> params) {
        return mMyApiService.getHomeCourseList(type,params);
    }
}
