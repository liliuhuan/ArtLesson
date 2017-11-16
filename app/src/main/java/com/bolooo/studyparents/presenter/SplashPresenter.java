package com.bolooo.studyparents.presenter;

import com.bolooo.studyparents.base.mvpbase.RxPresenter;
import com.bolooo.studyparents.contract.SplashContract;
import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.model.DataManager;
import com.bolooo.studyparents.model.http.respone.MyHttpResponse;
import com.bolooo.studyparents.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;
    private static final String _KEY = "AdUrl";
    private static final String CITY_KEY = "1";
    private DataManager dataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getWelcomeData() {
        addSubscribe(dataManager.fetchSplashInfo(_KEY)
                .compose(RxUtil.<MyHttpResponse<SplashEntity>>rxSchedulerHelper())
                .compose(RxUtil.<SplashEntity>handleMyResult())
                .subscribe(new Consumer<SplashEntity>() {
                    @Override
                    public void accept(SplashEntity splashEntity) {
                        mView.showContent(splashEntity);
                        startCountDown();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        jumpToOther();
                    }
                }));
    }

    private void jumpToOther() {
        if (dataManager.isFrist()){
            mView.jumpToWelcom();
        }else {
            mView.jumpToMain();
        }
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        jumpToOther();
                    }
                })
        );
    }

    @Override
    public void getCityData() {
        addSubscribe(dataManager.fetchCityInfo(CITY_KEY)
                .compose(RxUtil.<MyHttpResponse<String>>rxSchedulerHelper())
                .compose(RxUtil.<String>handleMyResult())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                dataManager.saveCityData(s);
            }
        }));
    }
}
