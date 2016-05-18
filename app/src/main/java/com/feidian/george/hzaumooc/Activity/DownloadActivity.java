package com.feidian.george.hzaumooc.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.feidian.george.hzaumooc.Fragment.Download.DownloadingFragment;
import com.feidian.george.hzaumooc.Fragment.Download.DownloadsFragment;
import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DownloadActivity extends FragmentActivity {
    private List<Fragment> fragments;

    @Bind(R.id.download_t_viewPager)
     ViewPager viewPager;
    @Bind(R.id.download_t_doing)
     TextView doing;
    @Bind(R.id.download_t_finish)
     TextView finish;
    @Bind(R.id.download_t_line)
     View line;

    private int line_width;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        // 初始化TextView动画
        setAnimation(1.0f,1.0f,1.2f,1.2f);

        initView();

    }
    // 根据传入的值来改变状态
    private void changeState(int arg0) { //设置选择的变化动画
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessage(msg);
        //根据ViewPage的移动改变字体的大小及颜色
        if (arg0 == 0) {
            finish.setTextColor(getResources().getColor(R.color.green));
            doing.setTextColor(getResources().getColor(R.color.gray_white));
            setAnimation(1.0f,1.0f,1.2f,1.2f);
        } else {
            doing.setTextColor(getResources().getColor(R.color.green));
            finish.setTextColor(getResources().getColor(R.color.gray_white));
            setAnimation(1.2f,1.2f,1.0f,1.0f);
        }
    }
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    private void setAnimation(float doing_scaleX,float doing_scaleY,float finish_scaleX,float finish_scaleY)
    {
        finish.animate().scaleX(finish_scaleX).setDuration(200);
        finish.animate().scaleY(finish_scaleY).setDuration(200);
        doing.animate().scaleX(doing_scaleX).setDuration(200);
        doing.animate().scaleY(doing_scaleY).setDuration(200);
    }
    private void initView()
    {
        fragments = new ArrayList<Fragment>();   //填充两个fragment
        fragments.add(new DownloadsFragment());
        fragments.add(new DownloadingFragment());
        line_width = getWindowManager().getDefaultDisplay().getWidth()
                / fragments.size();//计算指示栏的长度
        line.getLayoutParams().width = line_width;//设置指示栏的长度
        line.requestLayout();
        viewPager.setAdapter(new FragmentStatePagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                changeState(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                float tagerX = arg0 * line_width + arg2 / fragments.size();
                line.animate().translationX(tagerX).setDuration(0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        doing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });

        finish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
    }
}
