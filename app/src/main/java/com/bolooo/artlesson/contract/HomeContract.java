package com.bolooo.artlesson.contract;

import com.bolooo.artlesson.base.mvpbase.BasePresenter;
import com.bolooo.artlesson.base.mvpbase.BaseView;
import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.Map;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public interface HomeContract {
    interface View extends BaseView{
        void showAdDialog(List<AdEntity.AdvertisementsEntity> adEntityList);
        void showBanner(String[] images);
        void showContent(HomeDataEntity homeDataEntity);
        void startLocation();
    }

    interface Presenter extends BasePresenter<View>{
        void getAdData();
        void getBannerData();
        void getHomeCourseListData(Map<String, String> params);
        void checkPermissions(RxPermissions rxPermissions);
    }
}
