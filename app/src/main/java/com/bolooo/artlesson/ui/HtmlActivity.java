package com.bolooo.artlesson.ui;

import android.content.Context;
import android.content.Intent;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.BaseActivity;

/**
 * web网页封装的启动类
 */
public class HtmlActivity extends BaseActivity {

    private String url,title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_html;
    }

    public static void openHtmlActivity(Context activity, String url, String title) {
        Intent intent = new Intent(activity, HtmlActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected void onViewCreated() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected void initEventAndData() {

    }
}
