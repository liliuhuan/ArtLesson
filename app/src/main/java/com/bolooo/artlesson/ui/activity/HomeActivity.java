package com.bolooo.artlesson.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseActivity;
import com.bolooo.artlesson.ui.fragment.ChatFragment;
import com.bolooo.artlesson.ui.fragment.HomeFragment;
import com.bolooo.artlesson.ui.fragment.MineFragment;

import butterknife.BindView;

/**
 * 首页面
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private MineFragment mineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onViewCreated() {
        //UpdateBuilder.create().check();//检查更新
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void initEventAndData() {
        homeFragment = HomeFragment.newInstance();
        chatFragment = ChatFragment.newInstance();
        mineFragment = MineFragment.newInstance();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadMultipleRootFragment(R.id.content,0,homeFragment,chatFragment,mineFragment);

//        navigation.setSelectedItemId(R.id.navigation_dashboard);
//        showHideFragment(chatFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showHideFragment(homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    showHideFragment(chatFragment);
                    return true;
                case R.id.navigation_notifications:
                    showHideFragment(mineFragment);
                    return true;
            }
            return false;
        }
    };
}
