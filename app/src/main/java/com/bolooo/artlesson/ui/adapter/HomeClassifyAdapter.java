package com.bolooo.artlesson.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.adapter.BaseRecycleViewAdapter;
import com.bolooo.artlesson.base.adapter.BaseRecycleViewHolder;
import com.bolooo.artlesson.compant.ImageLoader;
import com.bolooo.artlesson.entity.HomeDataEntity;
import com.bolooo.artlesson.util.DensityUtil;

import butterknife.BindView;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public class HomeClassifyAdapter extends BaseRecycleViewAdapter<HomeDataEntity.DirectoryTypesEntity> {

    public HomeClassifyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getConvertViewId(int viewTypeviewType) {
        return R.layout.item_classify_layout;
    }

    @Override
    public BaseRecycleViewHolder<HomeDataEntity.DirectoryTypesEntity> getNewHolder(View view) {
        return new MyClassifyViewHolder(view);
    }

    public class MyClassifyViewHolder extends BaseRecycleViewHolder<HomeDataEntity.DirectoryTypesEntity> {
        @BindView(R.id.image_classify)
        ImageView imageClassify;

        public MyClassifyViewHolder(View view) {
            super(view);
        }

        @Override
        public void loadData(HomeDataEntity.DirectoryTypesEntity data, int position) {
            if (data == null) return;
            // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Constants.screenWidth*2/11,Constants.screenHeight/8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext,80),DensityUtil.dip2px(mContext,80));
            imageClassify.setLayoutParams(params);
            imageClassify.setPadding(0,DensityUtil.dip2px(mContext,10),0,DensityUtil.dip2px(mContext,10));
            ImageLoader.load(mContext,data.getSmallIcon(),imageClassify);
            imageClassify.setOnClickListener(v -> {
                if (lisenter != null) lisenter.onItemClick(data);
            });
        }
    }
    OnTypeItemClickLisenter lisenter ;

    public void setLisenter(OnTypeItemClickLisenter lisenter) {
        this.lisenter = lisenter;
    }
    public interface OnTypeItemClickLisenter{
        void onItemClick(HomeDataEntity.DirectoryTypesEntity  directoryTypesEntity);
    }
}
