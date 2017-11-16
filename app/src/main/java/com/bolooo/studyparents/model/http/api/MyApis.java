package com.bolooo.studyparents.model.http.api;

import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.model.http.Constants;
import com.bolooo.studyparents.model.http.respone.MyHttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求接口
 */

public interface MyApis {
    //闪屏
    @GET(Constants.SPLASH_URL)
    Flowable<MyHttpResponse<SplashEntity>> getSplashInfo(@Query("key") String key);
    //获取城市信息
    @GET(Constants.GET_CITY_LIST)
    Flowable<MyHttpResponse<String>> getCityAreas(@Query("param") String params);
}
