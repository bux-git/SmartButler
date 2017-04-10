package com.dqr.www.smartbutler.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description：SharedPreferences 工具类
 * Author：LiuYM
 * Date： 2017-04-10 11:57
 */

public class ShareUtil {
    private static final String FILE_NAME="smart_butler";

    public static SharedPreferences getShared(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context,String key,Object value){
        SharedPreferences preferences =getShared(context);
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
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(Context context,String key,Object defaultValue){
        SharedPreferences preferences=getShared(context);
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
     * @param context
     * @param key
     */
    public static void removeByKey(Context context,String key){
        SharedPreferences sp = getShared(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(key);
        editor.apply();
    }


    /**
     * 清除所有key-value
     * @param context
     */
    public static void clearAll(Context context){
        SharedPreferences sp = getShared(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.apply();
    }


}
