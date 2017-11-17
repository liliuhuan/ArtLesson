package com.bolooo.artlesson.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.RootFragment;
import com.bolooo.artlesson.contract.HomeContract;
import com.bolooo.artlesson.entity.AdEntity;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.model.http.Constants;
import com.bolooo.artlesson.presenter.HomePresenter;
import com.bolooo.artlesson.ui.HtmlActivity;
import com.bolooo.artlesson.ui.adapter.WelcomePagerAdapter;
import com.bolooo.artlesson.util.DensityUtil;
import com.bolooo.artlesson.util.LocationUtils;
import com.bolooo.artlesson.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * HOME页面
 */
public class HomeFragment extends RootFragment<HomePresenter> implements HomeContract.View, LocationUtils.OnLocationChangeListener {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_locate)
    TextView tvLocate;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_see_all)
    TextView tvSeeAll;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
   

    //礼物广告栏
    private LinearLayout ll_indicator;
    private ViewPager mViewPager;
    private View pointed;
    private int basicWidth;
    //请求参数
    private double latitude;//维度
    private double longitude;//经度
    private String typeId = "";
    private String cityId = "";
    private String cityName = "";
    private String areaId = "";
    private String areaName = "";
    private String minAge = "0";
    private String maxAge = "0";
    private String keyword = "";
    private String typeLevel = "";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.checkPermissions(new RxPermissions(_mActivity));
        mPresenter.getAdData();
        mPresenter.getBannerData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stateLoading();
                mPresenter.getHomeCourseListData(getParams());
            }
        });
        stateLoading();
    }

    @Override
    public void showAdDialog(List<AdEntity.AdvertisementsEntity> adEntityList) {
        AlertDialog builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog).create();
        //  builder.show();
        builder.getWindow().setContentView(R.layout.home_gift_layout);//设置弹出框加载的布局
        WindowManager.LayoutParams lp = builder.getWindow().getAttributes();
        lp.width = Constants.screenWidth;//定义宽度
        lp.height = Constants.screenHeight;//定义高度
        builder.getWindow().setAttributes(lp);
        builder.setCancelable(false);
        ll_indicator = (LinearLayout) builder.getWindow().findViewById(R.id.ll_indicator);
        mViewPager = (ViewPager) builder.getWindow().findViewById(R.id.viewpager);
        mViewPager.setPageMargin(DensityUtil.dip2px(_mActivity, 10));
        pointed = builder.getWindow().findViewById(R.id.pointed);
        initViewPager(adEntityList);
        builder.getWindow().findViewById(R.id.image_cancel).setOnClickListener(view -> builder.dismiss());
    }

    private void initViewPager(List<AdEntity.AdvertisementsEntity> adEntityList) {
        List<ImageView> mGuides = new ArrayList<ImageView>();
        if (adEntityList == null || adEntityList.size() == 0) return;
        for (int i = 0; i < adEntityList.size(); i++) {
            ImageView image = new ImageView(getActivity());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setAdjustViewBounds(true);
            String url = Constants.imageUrl + adEntityList.get(i).getAdImage() + "?w=" + Constants.screenWidth * 4 / 5 + "&h=" + Constants.screenWidth;
            Glide.with(_mActivity).load(url)
                    .bitmapTransform(new RoundedCornersTransformation(_mActivity, DensityUtil.dip2px(_mActivity, 5), 0))
                    .error(R.drawable.noimage)
                    .into(image);
            image.setPadding(Constants.screenWidth / 10, 0, Constants.screenWidth / 10, 0);
            mGuides.add(image);

            View v = new View(getActivity());
            v.setBackgroundResource(R.drawable.new_indicator_normal);
            int width = getResources().getDimensionPixelSize(R.dimen.point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) params.leftMargin = width;
            v.setLayoutParams(params);
            ll_indicator.addView(v);
        }
        ll_indicator.getChildAt(0).setBackgroundResource(R.drawable.new_indicator_select);
        pointed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pointed.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                View childAt = ll_indicator.getChildAt(1);
                View childAt1 = ll_indicator.getChildAt(0);
                if (childAt != null && childAt1 != null) {
                    basicWidth = childAt.getLeft() - childAt1.getLeft();
                }

            }
        });
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(mGuides);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        // adName.setText(adEntityList.get(0).getAdTitle());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float offset = basicWidth * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pointed.getLayoutParams();
                params.leftMargin = (int) offset;
                pointed.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //这里做切换ViewPager时，底部RadioButton的操作
                for (int i = 0; i < ll_indicator.getChildCount(); i++) {
                    ll_indicator.getChildAt(i).setBackgroundResource(R.drawable.new_indicator_normal);
                }
                ll_indicator.getChildAt(position).setBackgroundResource(R.drawable.new_indicator_select);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter.setLisenter(pos ->
                HtmlActivity.openHtmlActivity(_mActivity, adEntityList.get(pos).getAdUrl(), adEntityList.get(pos).getAdTitle()));
    }

    @Override
    public void showBanner(String[] images) {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR    显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE    显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE    显示圆形指示器和标题
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT    指示器居左
        //Banner.CENTER    指示器居中
        //Banner.RIGHT    指示器居右
        banner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        //banner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        if (images != null && images.length > 1) banner.isAutoPlay(true);
        else banner.isAutoPlay(false);
        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(_mActivity).load(Constants.imageUrl + url).into(view);
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener((view, position) -> {
//            if (position >= 1) {
//                AdverEntity.AdvertisementsEntity advertisementsEntity = advertisements.get(position - 1);
//                String adUrl = advertisementsEntity.getAdUrl();
//                if (!TextUtils.isEmpty(adUrl) && adUrl.contains("=")) {
//                    String[] split = adUrl.split("=");
//                    if (!TextUtils.isEmpty(split[1]))
//                        AdDetailActivity.openHtmlActivity(_mActivity, split[1]);
//                } else {
//                    if (!TextUtils.isEmpty(adUrl))
//                        HtmlActivity.openHtmlActivity(_mActivity, adUrl, "欢迎使用游学家");
//                }
//            }
        });
    }

    @Override
    public void showContent(HomeDataEntity homeDataEntity) {
        stateMain();
    }

    @Override
    public void startLocation() {
        LocationUtils.register(this);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocationUtils.unregister();
    }

    @Override
    public void onLocationChanged(BDLocation location) {
        if (location != null) {
            if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                cityName = location.getCity();
                tvLocate.setText(cityName);
            } else {
                tvLocate.setText("定位失败");
                startChooseCity();
                LocationUtils.unregister();
                ToastUtil.show("定位失败");
            }
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        mPresenter.getHomeCourseListData(getParams());
    }

    private Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        map.put("CityId", cityId);
        map.put("CityName", cityName);
        map.put("AreaId", areaId);
        map.put("AreaName", areaName);
        map.put("MinAge", minAge);
        map.put("MaxAge", maxAge);
        map.put("TypeId", typeId);
        map.put("MyLongitude", String.valueOf(longitude));
        map.put("MyLatitude", String.valueOf(latitude));
        map.put("Keyword", keyword);
        map.put("TypeLevel", typeLevel);//1:一级目录；2：二级目录
        Log.d("params--", map.toString());
        return map;
    }

    @OnClick({R.id.tv_locate, R.id.tv_gift, R.id.search_icon, R.id.tv_see_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_locate:
                startChooseCity();
                break;
            case R.id.tv_gift:
                
                break;
            case R.id.search_icon:
                break;
            case R.id.tv_see_all:
                break;
        }
    }

    private void startChooseCity() {
    }

    @Override
    public void stateError() {
        super.stateError();
        if (swipeRefresh!= null) swipeRefresh.setRefreshing(false);
    }
}
