package com.feidian.george.hzaumooc.Fragment.Detail;

import android.content.Intent;
import android.support.v4.app.Fragment;//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.Adapter.Detail.Resource.ResourceAdapter;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Adapter.Detail.Resource.GridAdapter;
import com.feidian.george.hzaumooc.View.TimeRecycleView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ResourceFragment extends Fragment{
    public static final String BUNDLE_UPDATETIME="updatetime";
    public static final String BUNDLE_PPT="ppt";
    public static final String BUNDLE_RESOURCENAME="resourcename";


    @Bind(R.id.detail_rl_teacherwords)
    TextView teacherwords;
    @Bind(R.id.detail_rl_timeline_layout)
    TimeRecycleView timeRecycleView;
    List<String>  resource;
    List<String> updatetime;
    List<String> resourcename;
    ResourceAdapter resourceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        resource=bundle.getStringArrayList(BUNDLE_PPT);
        updatetime=bundle.getStringArrayList(BUNDLE_UPDATETIME);
        resourcename=bundle.getStringArrayList(BUNDLE_RESOURCENAME);
        View rootView = inflater.inflate(R.layout.f_detail_r_timelayout,container,false);
        ButterKnife.bind(this,rootView);
        resourceAdapter =new ResourceAdapter(getActivity(),resource,updatetime,resourcename);
        timeRecycleView.setAdapter(resourceAdapter);
        timeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
