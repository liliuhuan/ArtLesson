package com.bolooo.studyparents.ui;

import android.widget.ImageView;

import com.bolooo.studyparents.MainActivity;
import com.bolooo.studyparents.R;
import com.bolooo.studyparents.base.mvpbase.BaseMVPActivity;
import com.bolooo.studyparents.compant.ImageLoader;
import com.bolooo.studyparents.contract.SplashContract;
import com.bolooo.studyparents.entity.SplashEntity;
import com.bolooo.studyparents.presenter.SplashPresenter;
import com.bolooo.studyparents.util.IntentUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;


public class SplashActivity extends BaseMVPActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.splash_iv)
    ImageView splashIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
        mPresenter.getCityData();
    }

    @Override
    public void showContent(SplashEntity entity) {
        ImageLoader.load(this, entity.FieldValue, splashIv);
    }

    @Override
    public void jumpToMain() {
        IntentUtils.startIntent(this, MainActivity.class);
    }

    @Override
    public void jumpToWelcom() {
        IntentUtils.startIntent(this, WelcomeActivity.class);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(splashIv);
        super.onDestroy();
    }

}
