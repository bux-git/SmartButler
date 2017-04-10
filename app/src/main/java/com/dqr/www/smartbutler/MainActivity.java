package com.dqr.www.smartbutler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dqr.www.smartbutler.core.BaseActivity;
import com.dqr.www.smartbutler.fragment.ButlerFragment;
import com.dqr.www.smartbutler.fragment.GirlFragment;
import com.dqr.www.smartbutler.fragment.UserFragment;
import com.dqr.www.smartbutler.fragment.WeiChatFragment;
import com.dqr.www.smartbutler.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import static com.dqr.www.smartbutler.R.id.tabLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFatSetting;

    private static String[] mTitles;
    private List<Fragment> mFragments;

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main, R.string.app_name, MODE_TITLE);
    }

    @Override
    protected void setUpView() {
        mTabLayout = (TabLayout) findViewById(tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mFatSetting = (FloatingActionButton) findViewById(R.id.fat_setting);

        mFatSetting.setVisibility(View.GONE);
        mFatSetting.setOnClickListener(this);

        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mFatSetting.setVisibility(View.GONE);
                } else {
                    mFatSetting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mTitles = getResources().getStringArray(R.array.main_tab_item);
        mFragments = new ArrayList<>();
        mFragments.add(ButlerFragment.getInstance());
        mFragments.add(WeiChatFragment.getInstance());
        mFragments.add(GirlFragment.getInstance());
        mFragments.add(UserFragment.getInstance());

        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fat_setting:
                SettingActivity.start(MainActivity.this);
                break;
        }
    }
}
