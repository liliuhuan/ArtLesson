package com.bolooo.artlesson.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bolooo.artlesson.R;
import com.bolooo.artlesson.base.adapter.BaseRecycleViewAdapter;
import com.bolooo.artlesson.base.adapter.BaseRecycleViewHolder;
import com.bolooo.artlesson.compant.ImageLoader;
import com.bolooo.artlesson.entity.HomeDataEntity;

import butterknife.BindView;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public class HomeCourseAdapter extends BaseRecycleViewAdapter<HomeDataEntity.CourseShowResponsesEntity> {



    public HomeCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public int getConvertViewId(int viewTypeviewType) {
        return R.layout.item_home_teacher_layout;
    }

    @Override
    public BaseRecycleViewHolder<HomeDataEntity.CourseShowResponsesEntity> getNewHolder(View view) {
        return new MyHomeLookViewHolder(view);
    }

    public class MyHomeLookViewHolder extends BaseRecycleViewHolder<HomeDataEntity.CourseShowResponsesEntity> {
        @BindView(R.id.image_teacher)
        ImageView imageTeacher;
        @BindView(R.id.tv_teacher_name)
        TextView tvTeacherName;
        @BindView(R.id.tv_teacher_course)
        TextView tvTeacherCourse;
        @BindView(R.id.tv_teacher_tag)
        TextView tvTeacherTag;
        @BindView(R.id.tv_locate_name)
        TextView tvLocateName;
        @BindView(R.id.tv_locate_distance)
        TextView tvLocateDistance;
        @BindView(R.id.image_course)
        ImageView imageCourse;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_sign_state)
        TextView tvSignState;
        @BindView(R.id.tv_course_name)
        TextView tvCourseName;
        @BindView(R.id.item_course_score)
        RatingBar itemCourseScore;
        @BindView(R.id.tv_sign_number)
        TextView tvSignNumber;

        public MyHomeLookViewHolder(View view) {
            super(view);
        }

        @Override
        public void loadData(HomeDataEntity.CourseShowResponsesEntity data, int position) {
            if (data == null) return;
            ImageLoader.load(mContext,data.getFirstImg() + "?w=1000", imageCourse);
            ImageLoader.load(mContext,data.getHeadPhoto(), imageTeacher);
            tvCourseName.setText("《" + data.getCourseName() + "》");
            tvYear.setText(data.getMinAge() + "-" + data.getMaxAge() + "岁");
            String dataTeacherName = data.getTeacherName();
            tvTeacherName.setText(dataTeacherName);
            tvTeacherCourse.setText(data.getJobTitle());

            double averageScore = data.getAverageScore();
            itemCourseScore.setStepSize((float) 0.1);
            itemCourseScore.setRating((float) averageScore);

            if (!TextUtils.isEmpty(data.getAreaName())) {
                tvLocateName.setText(data.getAreaName());
            } else {
                tvLocateName.setText("位置未知");
            }
            String officialTitle = data.getOfficialTitle();
            String titleColor = data.getTitleColor();
            if (!TextUtils.isEmpty(titleColor)) {
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(5);
                drawable.setColor(Color.parseColor(titleColor));
                tvTeacherTag.setBackground(drawable);
            }

            if (TextUtils.isEmpty(officialTitle)) {
                tvTeacherTag.setVisibility(View.GONE);
            } else {
                tvTeacherTag.setVisibility(View.VISIBLE);
                tvTeacherTag.setText(officialTitle);
            }
            double distance = data.getDistance();
            if (distance >= 1000) {
                tvLocateDistance.setVisibility(View.VISIBLE);
                float distanceNum = (float) (distance / 1000.0);
                float num = (float) (Math.round(distanceNum * 100)) / 100;
                tvLocateDistance.setText("距离您 " + num + "km");
            } else {
                if (distance > 0) {
                    tvLocateDistance.setVisibility(View.VISIBLE);
                    tvLocateDistance.setText("距离您 " + distance + "m");
                } else {
                    tvLocateDistance.setText("");
                    tvLocateDistance.setVisibility(View.GONE);
                }
            }
            int commentCount = data.getCommentCount();
            if (averageScore == 0) {
                tvSignNumber.setText(mContext.getString(R.string.no_assis));
            } else {
                String str = "<font color='red'>" + (float) averageScore + "</font>" + " 分 " + commentCount + "条评价";
                tvSignNumber.setText(Html.fromHtml(str));
            }
            //报名状态
            switch (data.getCourseStatus()) {
                case 1:
                    tvSignState.setText("预热中");
                    break;
                case 2:
                    tvSignState.setText("报名中");
                    break;
                case 3:
                    tvSignState.setText("报名已满");
                    break;
            }
            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("courseId", data.getCourseId());
                bundle.putString("teacherId", data.getTeacherId());
                bundle.putString("teacherName", data.getTeacherName());
                bundle.putString("headPhotoUrl", data.getHeadPhoto());
//                IntentUtils.startIntentBundle(mContext, bundle, TeacherActivity.class);
            });
        }
    }
}
