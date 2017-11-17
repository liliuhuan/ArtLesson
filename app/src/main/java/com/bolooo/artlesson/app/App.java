package com.bolooo.artlesson.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.bolooo.artlesson.R;
import com.bolooo.artlesson.di.compant.AppComponent;
import com.bolooo.artlesson.di.compant.DaggerAppComponent;
import com.bolooo.artlesson.di.module.AppModule;
import com.bolooo.artlesson.di.module.HttpModule;
import com.bolooo.artlesson.entity.UpdateEntity;
import com.bolooo.artlesson.util.ToastUtil;

import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.callback.UpdateCheckCB;
import org.lzh.framework.updatepluginlib.callback.UpdateDownloadCB;
import org.lzh.framework.updatepluginlib.model.CheckEntity;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.model.UpdateParser;
import org.lzh.framework.updatepluginlib.strategy.UpdateStrategy;
import org.lzh.framework.updatepluginlib.util.HandlerUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;


import static com.bolooo.artlesson.model.http.Constants.UPDATE_APP_URL;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class App extends Application {
    private static App instance;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化数据库
        Realm.init(getApplicationContext());
        //初始化更新控件
        initUpdateConfig();
        //百度地图定位
        SDKInitializer.initialize(instance);
        //在子线程中完成其他初始化
       // InitializeService.start(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    /*设置检查更新配置*/
    private void initUpdateConfig(){
        UpdateConfig.getConfig()
                // 必填：初始化一个Application框架内使用
                .init(this)
                // 必填：数据更新接口,url与checkEntity两种方式任选一种填写
                //.url(Constants.UPDATE_APP_URL)
                .checkEntity(new CheckEntity().setMethod(org.lzh.framework.updatepluginlib.model.HttpMethod.GET).setUrl(UPDATE_APP_URL))
                // 必填：用于从数据更新接口获取的数据response中。解析出Update实例。以便框架内部处理
                .jsonParser(new UpdateParser() {
                    @Override
                    public Update parse(String response) {
                        /* 此处根据上面url或者checkEntity设置的检查更新接口的返回数据response解析出
                         * 一个update对象返回即可。更新启动时框架内部即可根据update对象的数据进行处理
                         */
                       // LogUtils.i("update===", response);
                        Update update = new Update(response);
                        UpdateEntity updateEntity = com.alibaba.fastjson.JSONObject.parseObject(response, UpdateEntity.class);
                        if(updateEntity!=null){
                            if(updateEntity.IsSuccess){

                                // 此apk包的更新时间
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                try {
                                    Date date = null;
                                    date = formatter.parse(updateEntity.Data.UpdateTime);
                                    update.setUpdateTime(date.getTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                // 此apk包的下载地址
                                update.setUpdateUrl(updateEntity.Data.DownloadUrl);
                                // 此apk包的版本号
                                update.setVersionCode(updateEntity.Data.VersionNum);
                                // 此apk包的版本名称
                                update.setVersionName(updateEntity.Data.VersionName);
                                // 此apk包的更新内容
                                update.setUpdateContent(updateEntity.Data.UpdateInfo);
                                // 此apk包是否为强制更新
                                update.setForced(updateEntity.Data.IsForcedUpdate);

                                // 是否显示忽略此次版本更新按钮
                                update.setIgnore(true);
                            }
                        }

                        return update;
                    }
                })
                // TODO: 2016/5/11 除了以上两个参数为必填。以下的参数均为非必填项。
                .checkCB(new UpdateCheckCB() {

                    @Override
                    public void onCheckError(int code, String errorMsg) {
                    }

                    @Override
                    public void onUserCancel() {
                    }

                    @Override
                    public void onCheckIgnore(Update update) {
                    }

                    @Override
                    public void onCheckStart() {
                        // 此方法的回调所处线程异于其他回调。其他回调所处线程为UI线程。
                        // 此方法所处线程为你启动更新任务是所在线程
                        HandlerUtil.getMainHandler().post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }

                    @Override
                    public void hasUpdate(Update update) {
                    }

                    @Override
                    public void noUpdate() {
                    }
                })
                // apk下载的回调
                .downloadCB(new UpdateDownloadCB(){
                    @Override
                    public void onUpdateStart() {
                    }

                    @Override
                    public void onUpdateComplete(File file) {
                        ToastUtil.show(R.string.update_finish);
                    }

                    @Override
                    public void onUpdateProgress(long current, long total) {
                    }

                    @Override
                    public void onUpdateError(int code, String errorMsg) {
                        ToastUtil.show(R.string.update_fail);
                    }
                }).strategy(new UpdateStrategy() {
            @Override
            public boolean isShowUpdateDialog(Update update) {
                // 是否在检查到有新版本更新时展示Dialog。
                return true;
            }

            @Override public boolean isAutoInstall() {
                return true;
            }

            @Override
            public boolean isShowDownloadDialog() {
                // 在APK下载时。是否显示下载进度的Dialog
                return true;
            }
        });
    }
}
