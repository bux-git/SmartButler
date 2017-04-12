package com.dqr.www.smartbutler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseActivity;
import com.dqr.www.smartbutler.entity.User;
import com.dqr.www.smartbutler.utils.ToastUtil;
import com.dqr.www.smartbutler.utils.ValidationUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Description：忘记和重置密码
 * Author：LiuYM
 * Date： 2017-04-12 14:35
 */

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtOld;
    private EditText mEtNew;
    private EditText mEtNew2;
    private EditText mEtEmail;

    private Button mBtnReset;
    private Button mBtnSendEmail;

    public static void start(Context context){
        Intent intent = new Intent(context,ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_forgetpassword_layout, R.string.forget_reset_password);
    }

    @Override
    protected void setUpView() {
        mEtOld = (EditText) findViewById(R.id.et_old);
        mEtNew = (EditText) findViewById(R.id.et_new);
        mEtNew2 = (EditText) findViewById(R.id.et_new2);
        mEtEmail = (EditText) findViewById(R.id.et_email);

        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnSendEmail = (Button) findViewById(R.id.btn_send_email);

        mBtnReset.setEnabled(false);
        mBtnSendEmail.setEnabled(false);
        mBtnReset.setOnClickListener(this);
        mBtnSendEmail.setOnClickListener(this);

        mEtOld.addTextChangedListener(resetWatcher);
        mEtNew.addTextChangedListener(resetWatcher);
        mEtNew2.addTextChangedListener(resetWatcher);

        mEtEmail.addTextChangedListener(sendEmailWatcher);

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reset:
                resetPwd();
                break;
            case R.id.btn_send_email:
                sendEmail();
                break;
        }
    }


    /**
     * 发送邮件
     */
    private void sendEmail() {
        String email=mEtEmail.getText().toString().trim();
        if(!ValidationUtil.isEmail(email)){
            ToastUtil.showShort(ToastUtil.WARNING, R.string.register_error_email);
            return;
        }
        dialogShow("");
        User.resetPasswordByEmail(email, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    ToastUtil.showShort(ToastUtil.SUCCESS, R.string.forget_send_email_success);
                    finish();
                }else{
                    ToastUtil.showShort(ToastUtil.ERROR, e.toString());
                }
                dialogDismiss();
            }
        });
    }

    /**
     * 重置密码
     */
    private void resetPwd() {
        String oldPwd=mEtOld.getText().toString().trim();
        String newPwd=mEtNew.getText().toString().trim();
        String newPwd2=mEtNew2.getText().toString().trim();
        if(!newPwd.equals(newPwd2)){
            ToastUtil.showShort(ToastUtil.WARNING, R.string.register_no_same);
            return ;
        }

        dialogShow("");
        User.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    ToastUtil.showShort(ToastUtil.SUCCESS,R.string.forget_reset_success);
                    finish();
                }else{
                    ToastUtil.showShort(ToastUtil.ERROR, e.toString());
                }
                dialogDismiss();
            }
        });
    }


    TextWatcher resetWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBtnReset.setEnabled(!ValidationUtil.isEmpty(mEtOld.getText().toString().trim(),
                    mEtNew.getText().toString().trim(),
                    mEtNew2.getText().toString().trim()));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher sendEmailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBtnSendEmail.setEnabled(!ValidationUtil.isEmpty(mEtEmail.getText().toString().trim()));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
