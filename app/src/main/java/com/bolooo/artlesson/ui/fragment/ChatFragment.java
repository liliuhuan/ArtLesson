package com.bolooo.artlesson.ui.fragment;

import android.os.Bundle;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseFragment;

/**

 */
public class ChatFragment extends BaseFragment {


    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initEventAndData() {

    }


}
