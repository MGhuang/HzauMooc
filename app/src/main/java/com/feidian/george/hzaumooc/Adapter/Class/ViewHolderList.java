package com.feidian.george.hzaumooc.Adapter.Class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.Listener.Main.ItemOnClickListener;
import com.feidian.george.hzaumooc.Listener.Main.MoreOnClickListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/14.
 */
class ViewHolderList extends RecyclerView.ViewHolder{

    private ItemOnClickListener itemOnClickListener;
    @Bind(R.id.class_rl_title)
    TextView title;
    @Bind(R.id.class_rl_list)
    NoScrollListView listView;

    public ViewHolderList(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setListener(ArrayList<?> list, Context context)  //重新设置监听器
    {
        setItemOnClickListener(list,context);
        listView.setOnItemClickListener(itemOnClickListener);
    }
    private void setItemOnClickListener(ArrayList<?> list, Context context)
    {
        if(itemOnClickListener == null)
        {
            itemOnClickListener = new ItemOnClickListener(list,context);
        }
        else
        {
            itemOnClickListener.setList(list);
        }
    }
}
