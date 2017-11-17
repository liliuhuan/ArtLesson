package com.bolooo.artlesson.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.bolooo.artlesson.app.App;
import com.bolooo.artlesson.entity.ConfigEntity;
import com.bolooo.artlesson.ui.HtmlActivity;
import com.bolooo.artlesson.ui.activity.LoginActivity;
import com.bolooo.artlesson.util.DensityUtil;
import com.bolooo.artlesson.util.IntentUtils;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : 跳转工具类
 * =======================================
 */

public class HomeJumpManager {
    private Context context;
    protected DisplayMetrics dm;
    String token;

    public HomeJumpManager(Context context) {
        this.context = context;
        dm = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        token = App.getAppComponent().preferencesHelper().getToken();
    }

    /**
     * 处理跳转
     *
     * @param homeModel
     */
    public void jump(ConfigEntity.DataEntity.BlocksEntity homeModel) {
        if (homeModel == null) {
            return;
        }
        /*
            0 点击无效果
            1 点击打开链接WEB页
            2 查找链接对应的原生界面。打开原生界面
         */
        int blockActionType = homeModel.getBlockActionType();
        String webUrl = homeModel.getBlockLinkUrl();
        switch (blockActionType) {
            case 0:

                break;
            case 1:
                if (homeModel.getIsAddToken() == 1) {
                    if (TextUtils.isEmpty(token)) {
                        Intent intent = new Intent();
                        intent.setClass(context, LoginActivity.class);
                        context.startActivity(intent);
                        return;
                    }
                    webUrl = webUrl + "?token=" + token;
                }
                HtmlActivity.openHtmlActivity(context, webUrl, "");
                break;
            case 2:
                //jumpApp(webUrl);
                break;
        }

    }

//    private void jumpApp(String webUrl) {
//        if (TextUtils.isEmpty(webUrl))return;
//        if (checkLogin()){
//            if (webUrl.contains("http://uxueja.com/vue/teacher/lesson")){//教师页（课程页）
//                String cid = "";
//                String tid = "";
//                if (webUrl.contains("?")){
//                    String substring = webUrl.substring(webUrl.indexOf("?")+1, webUrl.length());
//                    if (substring.contains("&")){
//                        String[] split = substring.split("&");
//                        if (split != null && split.length>0){
//                            if(split[0].contains("=")){
//                                String[] splitKey = split[0].split("=");
//                                tid = splitKey[1];
//                            }
//                            if(split[1].contains("=")){
//                                String[] splitKey = split[1].split("=");
//                                cid = splitKey[1];
//                            }
//                        }
//                    }
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("courseId", cid);
//                bundle.putString("teacherId", tid);
//                IntentUtils.startIntentBundle( context, bundle, TeacherActivity.class);
//            }else if (webUrl.contains("http://uxueja.com/vue/uticket")){//游票商城页
//                IntentUtils.startIntent(context, UTicketActivity.class);
//            }else if (webUrl.contains("http://uxueja.com/vue/result")){//检索页面
//                String keyword = "";
//                String officalTitle = "";
//                if (webUrl.contains("?")){
//                    String substring = webUrl.substring(webUrl.indexOf("?")+1, webUrl.length());
//                    if (substring.contains("&")){
//                        String[] split = substring.split("&");
//                        if (split != null && split.length>0){
//                            if(split[2].contains("=")){
//                                String substring1 = split[2].substring(split[2].indexOf("=")+1, split[2].length());
//                                if (!TextUtils.isEmpty(substring1))keyword = substring1;
//                            }
//                            if(split[3].contains("=")){
//                                String substring1 = split[3].substring(split[3].indexOf("=")+1, split[3].length());
//                                if (!TextUtils.isEmpty(substring1))officalTitle = substring1;
//                            }
//                        }
//                    }
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("name", keyword);
//                bundle.putString("officalTitle", officalTitle);
//                IntentUtils.startIntentBundle(context, bundle, NewSearchReultActivity.class);
//            }else if (webUrl.contains("http://uxueja.com/vue/myfocus/foTeacher")){//我的关注讲师
//                Intent intent=new Intent(context,MyFocusActivity.class);
//                intent.putExtra("current",0);
//                context.startActivity(intent);
//            }else if (webUrl.contains("http://uxueja.com/vue/myfocus/foClass")){//我的关注课程
//                Intent intent=new Intent(context,MyFocusActivity.class);
//                intent.putExtra("current",0);
//                context.startActivity(intent);
//            }else if (webUrl.contains("http://uxueja.com/vue/myBaby")){//我的宝贝
//                IntentUtils.startIntent(context, MyBabyActivity.class);
//            }else if (webUrl.contains("http://uxueja.com/vue/vip")){//VIP
//                IntentUtils.startIntent(context, VipActivity.class);
//            }
//        }
//    }

    /**
     * 检查用户是否登录，未登录直接跳登录页
     * @return
     */
    private boolean checkLogin(){
        if(DensityUtil.isLogin()){
            return true;
        }else {
            IntentUtils.startIntent(context, LoginActivity.class);
            return false;
        }
    }
}
