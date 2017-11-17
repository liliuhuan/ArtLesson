package com.bolooo.artlesson.model.http.api;

import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.entity.SplashEntity;
import com.bolooo.artlesson.model.http.Constants;
import com.bolooo.artlesson.model.http.respone.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    //得到首页广告
    @GET(Constants.MAIN_AD_URL)
    Flowable<MyHttpResponse<AdEntity>> getMainAd();
    /**
     * 获取首页的广告轮播图
     * @param
     * @return
     */
    @GET(Constants.ADVERTISEMENT_IMGAES)
    Flowable<MyHttpResponse<AdEntity>> getBannerListInfo(@Query("type") int type);
    /**
     * 找课堂 for version 1.7.0 add in 2017-10-13
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.NEW_HOME_DATA_URL)
    Flowable<MyHttpResponse<HomeDataEntity>> getHomeCourseList(@Query("home") int home, @FieldMap Map<String, String> fields);
}
