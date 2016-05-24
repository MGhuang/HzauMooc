package com.feidian.george.hzaumooc.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.feidian.george.hzaumooc.Fragment.Detail.ResourceFragment;
import com.feidian.george.hzaumooc.Fragment.Detail.VideoListFragment;
import com.feidian.george.hzaumooc.Listener.Main.ItemOnClickListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄宇 on 2016/5/15.
 */
public class DetailActivity extends BaseActivity{
    public static final String BUNDLE_DETAIL_CLASSNAME="class_name";
    public static final String BUNDLE_DETAIL_CLASSKIND="class_kind";
    public static final String BUNDLE_DETAIL_TEACHER="class_teacher";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.detail_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.detail_viewpager)
    ViewPager viewPager;
    @Bind(R.id.background_image)
    ImageView background_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle get=getIntent().getExtras();
        toolbar.setTitle(get.getString(BUNDLE_DETAIL_CLASSNAME));
        toolbar.setNavigationIcon(R.mipmap.go_back);
        Bundle send = new Bundle();
        //数据填充（缺少数据，暂时为静态）
        send.putStringArrayList(ResourceFragment.BUNDLE_RESOURCENAME, Main_StaticValue.Data(get.getString(BUNDLE_DETAIL_CLASSNAME)));
        send.putString(VideoListFragment.BUNDLE_TEACHER,get.getString(BUNDLE_DETAIL_TEACHER));
        send.putString(VideoListFragment.BUNDLE_CLASSNAME,get.getString(BUNDLE_DETAIL_CLASSNAME));

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        creator.add("教学文件", ResourceFragment.class, send);
        creator.add("教学视频", VideoListFragment.class, send);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//toorbar监听
        int id=item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return false;
    }
}
