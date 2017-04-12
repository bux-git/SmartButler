package com.dqr.www.smartbutler.application;

import android.app.Application;
import android.content.Context;

import com.dqr.www.smartbutler.BuildConfig;
import com.dqr.www.smartbutler.utils.Constant;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-05 19:12
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化腾讯Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID, BuildConfig.DEBUG);
        //初始化Bmob 默认初始化
        Bmob.initialize(this, Constant.BOMB_APP_ID);
    }

    public static Context getInstance() {
        return sInstance;
    }
}
