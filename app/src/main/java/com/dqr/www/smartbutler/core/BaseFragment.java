package com.dqr.www.smartbutler.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description：Fragment基类,
 * 如果使用懒加载，加载数据放入setLoadData
 * 为View 设置数据在setUpData中
 * Author：LiuYM
 * Date： 2017-04-06 11:54
 */

public abstract class BaseFragment extends Fragment {
    private boolean isViewCreated;//控件是否初始化完成
    private boolean isDataLoaded;//数据是否已经加载
    private boolean isLazyLoad;//是否进行懒加载

    /**
     * 布局Id
     *
     * @return
     */
    protected abstract int getContentResId();

    /**
     * 初始化View
     */
    protected abstract void setUpView(View view);

    /**
     * 加载数据
     */
    protected abstract void setLoadData();

    /**
     * 为View数据设置Data
     */
    protected abstract void setUpData();

    /**
     * 设置进行懒加载
     */
    public void enableLazyLoad() {
        isLazyLoad = true;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        checkIfLoadData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentResId(), container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        setUpView(view);
        if (!isLazyLoad) {
            setUpData();
        } else {
            if (savedInstanceState != null) {
                onRestoreInstanceState(savedInstanceState);
            }
            if (isDataLoaded) {
                setUpData();
            } else {
                checkIfLoadData();
            }

        }
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataLoaded = true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }

    private void checkIfLoadData() {
        //第一次加载数据
        if (getUserVisibleHint() && isViewCreated && !isDataLoaded) {
            setLoadData();
            setUpData();
            isDataLoaded = true;
        }
    }
}
