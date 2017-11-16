package com.bolooo.artlesson.ui;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseActivity;
import com.bolooo.artlesson.ui.activity.HomeActivity;
import com.bolooo.artlesson.ui.adapter.WelcomePagerAdapter;
import com.bolooo.artlesson.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 欢迎引导页面
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_begin)
    TextView tvBegin;
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    int[] images = {R.drawable.start01, R.drawable.start02, R.drawable.start03};
    WelcomePagerAdapter adapter;
    List<ImageView> mGuides;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onViewCreated() {
        loadData();
    }
    private void loadData() {
        mGuides = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView image = new ImageView(this);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(images[i]);
            mGuides.add(image);
        }
    }


    @Override
    protected void initEventAndData() {
        adapter = new WelcomePagerAdapter(mGuides);
        viewpager.setAdapter(adapter);
        adapter.setLisenter(pos -> {
            if (pos == 2){
                IntentUtils.startIntent(WelcomeActivity.this, HomeActivity.class);
                finish();
            }
        });
    }

    @OnClick({R.id.tv_skip, R.id.tv_begin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
            case R.id.tv_begin:
                IntentUtils.startIntent(this, HomeActivity.class);
                finish();
                break;
        }
    }
}
