package com.dqr.www.smartbutler.application;

import android.app.Application;

import com.dqr.www.smartbutler.BuildConfig;
import com.dqr.www.smartbutler.utils.Constant;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-05 19:12
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化腾讯Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID,BuildConfig.DEBUG);
    }
}
