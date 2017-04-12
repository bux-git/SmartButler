package com.dqr.www.smartbutler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dqr.www.smartbutler.MainActivity;
import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseActivity;
import com.dqr.www.smartbutler.entity.User;
import com.dqr.www.smartbutler.utils.Constant;
import com.dqr.www.smartbutler.utils.ShareUtil;
import com.dqr.www.smartbutler.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.dqr.www.smartbutler.R.id.et_username;

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

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

        mEtUserName.addTextChangedListener(this);
        mEtPassWord.addTextChangedListener(this);

        mBtnLogin.setEnabled(false);

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        boolean isKeep= (boolean) ShareUtil.get(Constant.IS_KEEP,false);
        mCkbMemory.setChecked(isKeep);
        if(isKeep){
            mEtPassWord.setText((String)ShareUtil.get(Constant.USER_PASSWORD,""));
            mEtUserName.setText((String) ShareUtil.get(Constant.USER_NAME,""));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keepPwd();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                RegisterActivity.start(this);
                break;
            case R.id.tv_forget:
                ForgetPasswordActivity.start(this);
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        User user = new User();
        user.setUsername(mEtUserName.getText().toString().trim());
        user.setPassword(mEtPassWord.getText().toString().trim());

        dialogShow("");
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){//验证帐号成功
                    if(user.getEmailVerified()){
                        MainActivity.start(LoginActivity.this);
                        finish();
                    }else{//邮箱未验证
                        ToastUtil.showShort(ToastUtil.ERROR,R.string.email_verified);
                    }
                }else{//失败
                    ToastUtil.showShort(ToastUtil.ERROR, e.toString());
                }
                dialogDismiss();
            }
        });
    }

    /**
     * 检测输入信息
     */
    private boolean checkData() {
        if (TextUtils.isEmpty(mEtUserName.getText().toString().trim())
                || TextUtils.isEmpty(mEtPassWord.getText().toString().trim())) {
            return false;
        } else {
           return true;
        }
    }


    /**
     * 记住密码
     */
    private void keepPwd() {
        if(mCkbMemory.isChecked()&&checkData()){
            ShareUtil.put(Constant.IS_KEEP,true);
            ShareUtil.put(Constant.USER_NAME,mEtUserName.getText().toString().trim());
            ShareUtil.put(Constant.USER_PASSWORD,mEtPassWord.getText().toString().trim());
        }else{
            ShareUtil.put(Constant.IS_KEEP,false);
            ShareUtil.removeByKey(Constant.USER_NAME);
            ShareUtil.removeByKey(Constant.USER_PASSWORD);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mBtnLogin.setEnabled(checkData());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
