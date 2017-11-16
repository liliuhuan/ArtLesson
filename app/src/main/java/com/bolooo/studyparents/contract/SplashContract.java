package com.bolooo.studyparents.contract;

import com.bolooo.studyparents.base.mvpbase.BasePresenter;
import com.bolooo.studyparents.base.mvpbase.BaseView;
import com.bolooo.studyparents.entity.SplashEntity;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public interface SplashContract {
    interface View extends BaseView {
        void showContent(SplashEntity entity);

        void jumpToMain();

        void jumpToWelcom();
    }

    interface Presenter extends BasePresenter<View>{
        void getWelcomeData();
        void getCityData();
    }
}
