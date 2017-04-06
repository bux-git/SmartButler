package com.dqr.www.smartbutler;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dqr.www.smartbutler.core.BaseActivity;

import static com.dqr.www.smartbutler.R.id.tabLayout;

public class MainActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main,R.string.app_name,MODE_TITLE);
    }

    @Override
    protected void setUpView() {
        mTabLayout = (TabLayout) findViewById(tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }


}
