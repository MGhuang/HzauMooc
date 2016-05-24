package com.feidian.george.hzaumooc.Adapter.Detail.Resource;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Download;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  Context context;
    private  LayoutInflater layoutInflater;
    private List<String> ppt_resource;
    private List<String> ppt_updatetime;
    private List<String> ppt_resourcename;
    public ResourceAdapter(Context context)
    {
        this(context,null,null,null);
    }
    public ResourceAdapter(Context context,List<String> ppt_resource,List<String> ppt_updatetime,List<String> ppt_resourcename)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.ppt_resource=ppt_resource;
        this.ppt_resourcename=ppt_resourcename;
        this.ppt_updatetime=ppt_updatetime;
        if( ppt_resource == null || ppt_updatetime == null )
        {
            this.ppt_resource = Arrays.asList(context.getResources().getStringArray(R.array.static_resourceweb));
            this.ppt_updatetime = Arrays.asList(context.getResources().getStringArray(R.array.static_resourceupdate));
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderTime(layoutInflater.inflate(R.layout.f_detail_r_timelayout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderTime)
        {
            ((ViewHolderTime)holder).titlename.setText(ppt_resourcename.get(position));
            ((ViewHolderTime)holder).updatetime.setText(ppt_updatetime.get(position));
            ((ViewHolderTime)holder).setOnClickLinster(position);
        }
    }

    @Override
    public int getItemCount() {
        return ppt_resource.size();
    }
    class ViewHolderTime extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @Bind(R.id.detail_rlt_linear)
        LinearLayout linearLayout;
        @Bind(R.id.detail_rlt_titlename)
        TextView titlename;
        @Bind(R.id.detail_rlt_updatetime)
        TextView updatetime;

        private int position;

        public ViewHolderTime(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setOnClickLinster(int position)
        {
            this.position=position;
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Download.startDownload(context,ppt_resource.get(position),ppt_resourcename.get(position)+".ppt");
        }
    }
}
