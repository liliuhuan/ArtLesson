package com.bolooo.artlesson.contract;

import com.bolooo.artlesson.base.mvpbase.BasePresenter;
import com.bolooo.artlesson.base.mvpbase.BaseView;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public interface LoginContract {
    interface View extends BaseView{
        void jumpToMain();
    }

    interface Presenter extends BasePresenter<LoginContract.View>{
        void postLogin(String phone,String code);
    }
}
