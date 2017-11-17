package com.bolooo.artlesson.ui;

import android.widget.ImageView;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.mvpbase.BaseMVPActivity;
import com.bolooo.artlesson.compant.ImageLoader;
import com.bolooo.artlesson.contract.SplashContract;
import com.bolooo.artlesson.entity.SplashEntity;
import com.bolooo.artlesson.presenter.SplashPresenter;
import com.bolooo.artlesson.ui.activity.HomeActivity;
import com.bolooo.artlesson.util.IntentUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * 启动页面
 */

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
        IntentUtils.startIntent(this, HomeActivity.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void jumpToWelcom() {
        IntentUtils.startIntent(this, WelcomeActivity.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
