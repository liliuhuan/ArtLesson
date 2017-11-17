package com.bolooo.artlesson.presenter;

import android.Manifest;

import com.bolooo.artlesson.base.mvpbase.RxPresenter;
import com.bolooo.artlesson.contract.HomeContract;
import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.model.DataManager;
import com.bolooo.artlesson.util.RxUtil;
import com.bolooo.artlesson.widget.CommonSubscriber;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    DataManager mDataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getAdData() {
        addSubscribe(mDataManager.fetchMainAdInfo()
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleMyResult())
                .subscribe(new Consumer<AdEntity>() {
                    @Override
                    public void accept(@NonNull AdEntity adEntity) throws Exception {
                        if (adEntity != null) {
                            List<AdEntity.AdvertisementsEntity> adEntityList = adEntity.getAdvertisements();
                            int adNum = mDataManager.getAdNumber();
                            if (adNum < adEntity.getVersionNum()) {
                                if (adEntityList != null && adEntityList.size() > 0)
                                    mView.showAdDialog(adEntityList);
                               mDataManager.setAdNumber(adEntity.getVersionNum());
                            }
                        }
                    }
                })
        );
    }

    @Override
    public void getBannerData() {
        addSubscribe(mDataManager.fetchMainBannerInfo(1)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleMyResult()).subscribe(new Consumer<AdEntity>() {
                    @Override
                    public void accept(@NonNull AdEntity adEntity) throws Exception {
                        if (adEntity != null) {
                            List<AdEntity.AdvertisementsEntity> advertisements = adEntity.getAdvertisements();
                            if (advertisements != null && !advertisements.isEmpty()) {
                                String[] images = new String[advertisements.size()];
                                for (int i = 0; i < advertisements.size(); i++) {
                                    images[i] = advertisements.get(i).getAdImage();
                                }
                               mView.showBanner(images);
                            }
                        }
                    }
                }));
    }

    @Override
    public void getHomeCourseListData(Map<String, String> params) {
        addSubscribe(mDataManager.fetchMainCourseListInfo(170,params)
                    .compose(RxUtil.rxSchedulerHandlerResult())
                    .subscribeWith(new CommonSubscriber<HomeDataEntity>(mView, false) {
                        @Override
                        public void onNext(HomeDataEntity homeDataEntity) {
                            mView.showContent(homeDataEntity);
                        }
                    })
        );
    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)//网络定位权限
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            mView.startLocation();
                        } else {
                            mView.showErrorMsg("下载应用需要文件写入权限哦~");
                        }
                    }
                })
        );
    }
}
