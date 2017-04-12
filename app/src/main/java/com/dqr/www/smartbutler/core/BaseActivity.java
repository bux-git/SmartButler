package com.dqr.www.smartbutler.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.utils.SystemBarHelper;
import com.dqr.www.smartbutler.widget.LoadingDialog;

/**
 * Description：Activity 基类
 * Author：LiuYM
 * Date： 2017-04-05 19:14
 */

/**
 * 主要做的事情
 * 1.统一的属性
 * 2.统一的方法
 * 3.统一的接口
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    protected Toolbar mToolbar;
    public static final int MODE_BACK = 0;//返回
    public static final int MODE_NONE = 1;//无toolbar
    public static final int MODE_TITLE = 2;//标题

    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpContentView();

        SystemBarHelper.tintStatusBar(this,getResources().getColor(R.color.colorPrimary));

        setUpView();
        setUpData(savedInstanceState);
    }

    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpData(Bundle savedInstanceState);


    public void setContentView(int layoutResId) {
        setContentView(layoutResId, -1, -1, MODE_BACK);
    }

    public void setContentView(int layoutResId, int titleResId, int mode) {
        setContentView(layoutResId, titleResId, -1, mode);
    }

    public void setContentView(int layoutResId, int titleResId) {
        setContentView(layoutResId, titleResId, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId, int menuId, int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResId, menuId, mode);

    }


    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        if (mode != MODE_NONE) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            /**
             * 设置图标
             */
            if (mode == MODE_BACK) {
                TypedValue typedValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.homeAsUpIndicator, typedValue, true);
                mToolbar.setNavigationIcon(typedValue.resourceId);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClicked();
                }
            });
            setUpTitle(titleResId);
            setUpMenu(menuId);
        }

    }

    /**
     * 设置菜单栏
     *
     * @param menuId
     */
    protected void setUpMenu(int menuId) {
        if (mToolbar != null) {
            mToolbar.getMenu().clear();
            if (menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    /**
     * 设置标题
     *
     * @param titleResId
     */
    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && mToolbar != null) {
            mToolbar.setTitle(titleResId);
        }
    }

    protected void onNavigationBtnClicked() {
        finish();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void dialogShow(String msg){
        if(mLoadingDialog==null){
            if(TextUtils.isEmpty(msg)){
                msg = getString(R.string.load_msg);
            }
            mLoadingDialog = new LoadingDialog(this,msg);
        }
        mLoadingDialog.show();
    }

    public void dialogDismiss(){
        if(mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }
}
