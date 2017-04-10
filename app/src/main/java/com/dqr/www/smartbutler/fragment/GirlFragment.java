package com.dqr.www.smartbutler.fragment;

import android.view.View;
import android.widget.TextView;

import com.dqr.www.smartbutler.R;
import com.dqr.www.smartbutler.core.BaseFragment;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-10 8:50
 */

public class GirlFragment extends BaseFragment {
    private String mContent;
    private TextView textView;

    public static GirlFragment getInstance(){
        GirlFragment fragment = new GirlFragment();
        fragment.enableLazyLoad();
        return fragment;
    }


    @Override
    protected int getContentResId() {
        return R.layout.fragment_main_test;
    }

    @Override
    protected void setUpView(View view) {
        textView= (TextView) view.findViewById(R.id.tv_content);

    }


    @Override
    protected void setLoadData() {
        mContent="GirlFragment";
    }

    @Override
    protected void setUpData() {
        textView.setText(mContent);
    }
}
