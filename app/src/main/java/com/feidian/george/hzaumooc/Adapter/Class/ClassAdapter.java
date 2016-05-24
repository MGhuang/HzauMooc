package com.feidian.george.hzaumooc.Adapter.Class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.Interface.Main.GetValued;
import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<String,ArrayList<?>> map;
    private String[] keys;
    public ClassAdapter(Context context,Map<String,ArrayList<?>> map1)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        map=map1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderList(layoutInflater.inflate(R.layout.a_class_r_listview,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderList)
        {
            ((ViewHolderList)holder).title.setText(keys[position]);
            ((ViewHolderList)holder).listView.setAdapter(new ListAdapter(context,map.get(keys[position])));
            ((ViewHolderList)holder).setListener(map.get(keys[position]),context);
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }
    public void setKeys(String[] keys)
    {
        this.keys=keys;
    }

}
