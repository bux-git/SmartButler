package com.dqr.www.smartbutler.utils;

import android.util.Log;

import com.dqr.www.smartbutler.BuildConfig;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-10 11:07
 */

public class L {
    private static final String TAG="smartbutler";
    private static boolean DEBUG= BuildConfig.DEBUG;

    public static void d(String msg){
        if(DEBUG){
            Log.d(TAG,msg);
        }
    }

    public static void i(String msg){
        if(DEBUG){
            Log.i(TAG,msg);
        }
    }

    public static void w(String msg){
        if(DEBUG){
            Log.w(TAG,msg);
        }
    }

    public static void e(String msg){
        if(DEBUG){
            Log.e(TAG,msg);
        }
    }
}
