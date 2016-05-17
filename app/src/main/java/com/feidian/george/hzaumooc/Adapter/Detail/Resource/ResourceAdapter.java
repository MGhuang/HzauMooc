package com.feidian.george.hzaumooc.Adapter.Detail.Resource;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    LayoutInflater layoutInflater;
    List<String> resource;
    List<String> updatetime;
    List<String> resourcename;
    public ResourceAdapter(Context context)
    {
        this(context,null,null,null);
    }
    public ResourceAdapter(Context context,List<String> resource,List<String> updatetime,List<String> resourcename)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        if( resource == null || updatetime == null || resourcename == null)
        {
            resource = Arrays.asList(context.getResources().getStringArray(R.array.static_resourceweb));
            updatetime = Arrays.asList(context.getResources().getStringArray(R.array.static_resourceupdate));
            resourcename =Arrays.asList(context.getResources().getStringArray(R.array.static_resourcename));
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderTime(layoutInflater.inflate(R.layout.f_detail_r_timelayout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class ViewHolderTime extends RecyclerView.ViewHolder
    {
        public ViewHolderTime(View itemView) {
            super(itemView);
        }
    }
}
