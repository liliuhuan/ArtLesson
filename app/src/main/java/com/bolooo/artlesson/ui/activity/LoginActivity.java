package com.bolooo.artlesson.ui.activity;

import android.app.ProgressDialog;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        ProgressDialog progressBar = new ProgressDialog(this);

    }

    @Override
    protected void initEventAndData() {

    }
}
