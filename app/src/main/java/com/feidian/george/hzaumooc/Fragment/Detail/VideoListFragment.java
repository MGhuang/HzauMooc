package com.feidian.george.hzaumooc.Fragment.Detail;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.Adapter.Detail.VideoList.VideoListAdapter;
import com.feidian.george.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class VideoListFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String BUNDLE_CLASSNAME="class_name";
    public static final String BUNDLE_TEACHER="class_teacher";
    @Bind(R.id.main_swipe)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.main_list)
    RecyclerView recyclerView;

    VideoListAdapter videoListAdapter;
    android.os.Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            refreshLayout.setRefreshing(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        View rootView=inflater.inflate(R.layout.content_main,container,false);
        ButterKnife.bind(this,rootView);
        videoListAdapter = new VideoListAdapter(getActivity(),bundle.getString(BUNDLE_CLASSNAME),bundle.getString(BUNDLE_TEACHER));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(videoListAdapter);
        refreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onRefresh() {
        videoListAdapter.notifyDataSetChanged();
        handler.sendEmptyMessage(0);
    }
}
