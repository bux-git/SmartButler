package com.dqr.www.smartbutler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseActivity;
import com.dqr.www.smartbutler.entity.User;
import com.dqr.www.smartbutler.utils.ToastUtil;
import com.dqr.www.smartbutler.utils.ValidationUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Description：注册页面
 * Author：LiuYM
 * Date： 2017-04-11 10:47
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtUsername;
    private EditText mEtAge;
    private EditText mEtDesc;
    private EditText mEtPassword;
    private EditText mEtPassword2;
    private EditText mEtEmail;
    private RadioGroup mRgpSex;
    private Button mBtnRegister;

    private boolean mGender=true;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_register,R.string.reqister_title);
    }

    @Override
    protected void setUpView() {
        mEtUsername =(EditText) findViewById(R.id.et_username);
        mEtAge =(EditText) findViewById(R.id.et_age);
        mEtDesc =(EditText) findViewById(R.id.et_desc);
        mEtPassword =(EditText) findViewById(R.id.et_password);
        mEtPassword2 =(EditText) findViewById(R.id.et_password2);
        mEtEmail =(EditText) findViewById(R.id.et_email);
        mRgpSex = (RadioGroup) findViewById(R.id.rgp_sex);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(this);
        mRgpSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.rb_boy){
                    mGender=true;
                }else{
                    mGender=false;
                }
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                checkData();
                break;
        }
    }

    private void checkData() {
        String userName=mEtUsername.getText().toString().trim();
        String age=mEtAge.getText().toString().trim();
        String desc=mEtDesc.getText().toString().trim();
        String pwd1=mEtPassword.getText().toString().trim();
        String pwd2=mEtPassword2.getText().toString().trim();
        String email=mEtEmail.getText().toString().trim();

        if(ValidationUtil.isEmpty(userName,age,desc,pwd1,pwd2,email)){
            ToastUtil.showShort(ToastUtil.WARNING,R.string.register_no_full);
            return;
        }

        if(!pwd1.equals(pwd2)){
            ToastUtil.showShort(ToastUtil.WARNING,R.string.register_no_same);
            return;
        }

        if(!ValidationUtil.isEmail(email)){
            ToastUtil.showShort(ToastUtil.WARNING,R.string.register_error_email);
            return;
        }

        if(TextUtils.isEmpty(desc)){
            desc = getString(R.string.register_desc);
        }

        User user = new User();
        user.setUsername(userName);
        user.setAge(Integer.parseInt(age));
        user.setDesc(desc);
        user.setPassword(pwd1);
        user.setEmail(email);
        user.setGender(mGender);

        dialogShow("");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    ToastUtil.showShort(ToastUtil.SUCCESS,R.string.register_success);
                    finish();
                }else{
                    ToastUtil.showShort(ToastUtil.ERROR, e.toString());
                }
                dialogDismiss();
            }
        });
    }
}
