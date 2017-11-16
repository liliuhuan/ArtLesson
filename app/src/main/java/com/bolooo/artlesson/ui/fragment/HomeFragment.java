package com.bolooo.artlesson.ui.fragment;

import android.os.Bundle;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseFragment;

/**

 */
public class HomeFragment extends BaseFragment {

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

    }

}
