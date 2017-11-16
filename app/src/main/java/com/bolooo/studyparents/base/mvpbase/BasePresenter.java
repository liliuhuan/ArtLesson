package com.bolooo.studyparents.base.mvpbase;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
