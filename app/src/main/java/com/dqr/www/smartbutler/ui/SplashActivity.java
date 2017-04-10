package com.dqr.www.smartbutler.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dqr.www.smartbutler.MainActivity;
import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.utils.Constant;
import com.dqr.www.smartbutler.utils.ShareUtil;
import com.dqr.www.smartbutler.utils.UtilTools;

import static com.dqr.www.smartbutler.utils.Constant.FIRST_OPEN;
import static com.dqr.www.smartbutler.utils.Constant.HANDLER_FIRST_OPEN;

/**
 * Description：启动页
 * Author：LiuYM
 * Date： 2017-04-10 15:18
 */

/**
 * 1.判断是否第一次进入应用
 * 2.全屏显示
 * 3.设置字体
 * 4.延迟进入引导页或首页
 */
public class SplashActivity extends AppCompatActivity {
    public static final int HANDLER_SPLASH_DELAYED=2000;//首页延迟时间ms
    private TextView mTvSplash;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HANDLER_FIRST_OPEN:
                    gotoNext();
                break;
            }
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mTvSplash = (TextView) findViewById(R.id.tv_splash);
        UtilTools.setFonts(this,mTvSplash);
        mHandler.sendEmptyMessageDelayed(HANDLER_FIRST_OPEN, HANDLER_SPLASH_DELAYED);
    }



    private void gotoNext() {
        boolean isFirst= (boolean) ShareUtil.get(this,Constant.FIRST_OPEN,true);
        if(isFirst){//第一次打开应用 进入引导页
            GuideActivity.start(this);
            ShareUtil.put(this,FIRST_OPEN,false);
        }else{
            MainActivity.start(this);
        }
    }


    /**
     * 不允许返回
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
