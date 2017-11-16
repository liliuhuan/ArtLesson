package com.bolooo.artlesson.ui.fragment;

import android.os.Bundle;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseFragment;

/**

 */
public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {

    }
}
