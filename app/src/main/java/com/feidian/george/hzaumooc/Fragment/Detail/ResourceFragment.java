package com.feidian.george.hzaumooc.Fragment.Detail;

import android.support.v4.app.Fragment;//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ResourceFragment extends Fragment{
    @Bind(R.id.main_swipe)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.main_list)
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View rootView = inflater.inflate(R.layout.content_main,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }
}
