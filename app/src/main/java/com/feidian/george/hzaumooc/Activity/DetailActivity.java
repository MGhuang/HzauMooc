package com.feidian.george.hzaumooc.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.feidian.george.hzaumooc.Fragment.Detail.ResourceFragment;
import com.feidian.george.hzaumooc.Fragment.Detail.VideoListFragment;
import com.feidian.george.hzaumooc.Listener.Main.ItemOnClickListener;
import com.feidian.george.hzaumooc.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class DetailActivity extends BaseActivity{
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
        toolbar.setTitle(get.getString(ItemOnClickListener.BUNDLE_KEY_CLASSNAME));
        Bundle send = new Bundle();



        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        creator.add("教学视频", VideoListFragment.class, send);
        creator.add("教学文件", ResourceFragment.class, send);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
        setSupportActionBar(toolbar);

    }
}
