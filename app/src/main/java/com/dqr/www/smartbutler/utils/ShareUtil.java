package com.dqr.www.smartbutler.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dqr.www.smartbutler.application.BaseApplication;

/**
 * Description：SharedPreferences 工具类
 * Author：LiuYM
 * Date： 2017-04-10 11:57
 */

public class ShareUtil {
    private static final String FILE_NAME="smart_butler";

    public static SharedPreferences getShared(){
        return BaseApplication.getInstance().getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     * @param key
     * @param value
     */
    public static void put(String key,Object value){
        SharedPreferences preferences =getShared();
        SharedPreferences.Editor editor=preferences.edit();
        if(value instanceof String){
            editor.putString(key, (String) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if(value instanceof Long){
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    /**
     * 获取数据

     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key,Object defaultValue){
        SharedPreferences preferences=getShared();
        if(defaultValue instanceof String){
           return preferences.getString(key, (String) defaultValue);
        }else if(defaultValue instanceof Integer){
            return preferences.getInt(key, (Integer) defaultValue);
        }else if(defaultValue instanceof Boolean){
            return preferences.getBoolean(key, (Boolean) defaultValue);
        }else if(defaultValue instanceof Float){
            return preferences.getFloat(key, (Float) defaultValue);
        }else if(defaultValue instanceof Long){
            return preferences.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    /**
     * 移除key value数据对
     * @param key
     */
    public static void removeByKey(String key){
        SharedPreferences sp = getShared();
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(key);
        editor.apply();
    }


    /**
     * 清除所有key-value
     */
    public static void clearAll(){
        SharedPreferences sp = getShared();
        SharedPreferences.Editor editor = sp.edit();
        editor.apply();
    }


}
