package com.dqr.www.smartbutler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseActivity;

import static com.dqr.www.smartbutler.R.id.et_username;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtUserName;//用户名
    private EditText mEtPassWord;//密码
    private CheckBox mCkbMemory;//记住密码
    private Button mBtnLogin;//登录
    private Button mBtnRegister;//注册
    private TextView mTvForget;//忘记密码

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_login, R.string.login);
    }

    @Override
    protected void setUpView() {
        mEtUserName = (EditText) findViewById(et_username);
        mEtPassWord = (EditText) findViewById(R.id.et_password);
        mCkbMemory = (CheckBox) findViewById(R.id.ckb_memory);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mTvForget = (TextView) findViewById(R.id.tv_forget);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mTvForget.setOnClickListener(this);


    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                break;
            case R.id.btn_register:
                RegisterActivity.start(this);
                break;
            case R.id.tv_forget:
                break;
        }
    }
}
