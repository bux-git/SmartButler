package com.dqr.www.smartbutler.utils;

import android.content.Context;
import android.widget.Toast;

import com.dqr.www.smartbutler.application.BaseApplication;

import es.dmoral.toasty.Toasty;

public class ToastUtil {

    private static long mLastTime = 0;
    private static final int INTERVAL = 3000;

    public static final int INFO = 1;
    public static final int ERROR = 2;
    public static final int SUCCESS = 3;
    public static final int WARNING = 4;
    public static final int NORMAL = 5;


    public static String showShort(int toastType,int resId){
        String msg=BaseApplication.getInstance().getString(resId);
        showToast(toastType
                ,msg
                ,Toast.LENGTH_SHORT);
        return msg;
    }

    public static void showShort(int toastType,String str){
        showToast(toastType,str,Toast.LENGTH_SHORT);
    }

    public static String showLong(int toastType,int resId){
        String msg=BaseApplication.getInstance().getString(resId);
        showToast(toastType
                ,msg
                ,Toast.LENGTH_LONG);
        return msg;
    }

    public static void showLong(int toastType,String str){
        showToast(toastType,str,Toast.LENGTH_LONG);
    }


    /**
     * 检测是否已经过了时间限制
     * @return
     */
    private static boolean checkInterval() {
        boolean result = true;
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastTime < INTERVAL) {
            result = false;
        }
        mLastTime = currentTime;
        return result;

    }

    /**
     * 根据类型获取不同Toast
     * @param type
     * @return
     */
    private static void showToast(int type, String str,int duration) {
        Context context = BaseApplication.getInstance();
        if (checkInterval()) {
            switch (type) {
                case INFO:
                    Toasty.info(context,str, duration, true).show();
                    break;
                case ERROR:
                    Toasty.error(context,str, duration, true).show();
                    break;
                case NORMAL:
                    Toasty.normal(context,str, duration).show();
                    break;
                case SUCCESS:
                    Toasty.success(context,str, duration, true).show();
                    break;
                case WARNING:
                    Toasty.warning(context,str, duration, true).show();
                    break;
            }
        }

    }

}