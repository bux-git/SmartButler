package com.dqr.www.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Description：工具类
 * Author：LiuYM
 * Date： 2017-04-10 15:44
 */

public class UtilTools {

    public static void setFonts(Context context, TextView textView){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/FONT.TTF");
        textView.setTypeface(typeface);
    }
}
