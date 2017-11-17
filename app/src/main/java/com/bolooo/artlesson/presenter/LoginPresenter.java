package com.bolooo.artlesson.presenter;

import com.bolooo.artlesson.base.mvpbase.RxPresenter;
import com.bolooo.artlesson.contract.LoginContract;
import com.bolooo.artlesson.model.DataManager;

import javax.inject.Inject;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private DataManager dataManager;
    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void postLogin(String phone, String code) {

    }
}
