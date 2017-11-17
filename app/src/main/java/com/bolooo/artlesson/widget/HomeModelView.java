package com.bolooo.artlesson.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bolooo.artlesson.MainActivity;
import com.bolooo.artlesson.compant.ImageLoader;
import com.bolooo.artlesson.entity.ConfigEntity;
import com.bolooo.artlesson.model.http.Constants;

import java.util.List;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : 首页后台配置布局
 * =======================================
 */

public class HomeModelView  extends LinearLayout{
    private DisplayMetrics dm = new DisplayMetrics();
    HomeJumpManager homeJumpManager;

    public HomeModelView(Context context) {
        super(context);
    }

    public HomeModelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHomeModelData(Activity context, List<ConfigEntity.DataEntity> homeModel) {
        this.setOrientation(LinearLayout.VERTICAL);
        this.removeAllViews();
        ((MainActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        homeJumpManager = new HomeJumpManager(context);
        if (homeModel == null) {
            return;
        }

        int rowSize = homeModel.size();
        for (int i = 0; i < rowSize; i++) {
            ConfigEntity.DataEntity dataEntity = homeModel.get(i);
            String ratio = dataEntity.getRatio();
            LinearLayout childLay = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (Constants.screenWidth * Double.valueOf(ratio)));
            childLay.setOrientation(LinearLayout.HORIZONTAL);
            childLay.setLayoutParams(params);

            List<ConfigEntity.DataEntity.BlocksEntity> blocks = dataEntity.getBlocks();
            for (int j = 0; j < blocks.size(); j++) {
                ConfigEntity.DataEntity.BlocksEntity blocksEntity = blocks.get(j);
                String blockRatio = blocksEntity.getBlockRatio();
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                imgParams.width = (int) (Constants.screenWidth * Double.valueOf(blockRatio));
                imgParams.height = (int) (Constants.screenWidth * Double.valueOf(ratio));
                imageView.setLayoutParams(imgParams);
                childLay.addView(imageView);
                if (blocksEntity.getBlockBgUrl() != null) {
                    ImageLoader.load(context,blocksEntity.getBlockBgUrl(),imageView);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeJumpManager.jump(blocksEntity);
                    }
                });
            }
            this.addView(childLay);
        }
    }
}
