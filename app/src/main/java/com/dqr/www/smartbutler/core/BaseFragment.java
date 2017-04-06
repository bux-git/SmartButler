package com.dqr.www.smartbutler.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-06 11:54
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private boolean isVisibleToUser;//对用户可见
    private boolean isViewInitialized;
    private boolean isDataInitialized;
    private boolean isLazyLoadEnabled;

    public abstract void setUpView(View view);
    public abstract void setUpData();

    public void enableLazyLoad(){
        isLazyLoadEnabled=true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        Log.e(TAG, "setUserVisibleHint: "+isVisibleToUser );
        checkIfLoadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: " );

        if(!isLazyLoadEnabled){
            setUpView(view);
            setUpData();
        }else{
            setUpView(view);
            isViewInitialized=true;
            if(savedInstanceState!=null){
                onRestoreInstanceState(savedInstanceState);
            }
            if(isDataInitialized){
                setUpData();
            }else{
                checkIfLoadData();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized=false;
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.e(TAG, "onInflate: " );
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "onHiddenChanged: " );
    }

    private void checkIfLoadData(){
        if(isVisibleToUser&&isViewInitialized&&!isDataInitialized){
            isDataInitialized=true;
            setUpData();
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        isDataInitialized=true;
    }
}
