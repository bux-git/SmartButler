package com.dqr.www.smartbutler.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseActivity;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-10 10:04
 */

public class SettingActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_setting, R.string.setting);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }
}
