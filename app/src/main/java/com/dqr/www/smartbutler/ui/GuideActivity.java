package com.dqr.www.smartbutler.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dqr.www.smartbutler.MainActivity;
import com.dqr.www.smartbutler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-10 15:33
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<View> mViews;
    private List<ImageView> mPoints;

    private Button mBtnStart;
    private ImageView mBack;

    public static void start(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }


    private void initView() {
        View view1 = getLayoutInflater().inflate(R.layout.guide_one, null);
        View view2 = getLayoutInflater().inflate(R.layout.guide_two, null);
        View view3 = getLayoutInflater().inflate(R.layout.guide_three, null);

        mBtnStart = (Button) view3.findViewById(R.id.btn_start);
        ImageView point1 = (ImageView) findViewById(R.id.iv_Point1);
        ImageView point2 = (ImageView) findViewById(R.id.iv_Point2);
        ImageView point3 = (ImageView) findViewById(R.id.iv_Point3);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mBack = (ImageView) findViewById(R.id.iv_back);

        mBack.setOnClickListener(this);
        mBtnStart.setOnClickListener(this);

        mViews = new ArrayList<>();
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);


        mPoints = new ArrayList<>();
        mPoints.add(point1);
        mPoints.add(point2);
        mPoints.add(point3);
        setPointImg(0);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViews.get(position));
                return mViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position!=mViews.size()-1){
                    mBack.setVisibility(View.VISIBLE);
                }else{
                    mBack.setVisibility(View.GONE);
                }
                setPointImg(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void setPointImg(int position) {

        for (int i = 0; i < mPoints.size(); i++) {
            if (i == position) {
                mPoints.get(i).setImageResource(R.drawable.point_on);
            } else {
                mPoints.get(i).setImageResource(R.drawable.point_off);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                MainActivity.start(this);
                break;
            case R.id.iv_back:
                MainActivity.start(this);
                break;
        }
        finish();
    }
}
