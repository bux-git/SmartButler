package com.dqr.www.smartbutler.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dqr.www.smartbutler.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-11 19:20
 */

public class LoadingDialog extends Dialog {

    private Context mContext;
    private String mMessage;
    private TextView mTvMsg;

    public LoadingDialog(Context context,String msg) {
        super(context,R.style.loading_dialog_style);
        mContext=context;
        this.mMessage=msg;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_layout);
        setCanceledOnTouchOutside(false);
        mTvMsg = (TextView) findViewById(R.id.tv_LoadMsg);
        showMsg();
    }


    public void updateShowMsg(String msg){
        this.mMessage=msg;
        showMsg();
    }

    private void showMsg(){
        if(mTvMsg!=null&& !TextUtils.isEmpty(mMessage)){
            mTvMsg.setVisibility(View.VISIBLE);
            mTvMsg.setText(mMessage);
        }
    }
}
