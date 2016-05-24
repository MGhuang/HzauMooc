package com.feidian.george.hzaumooc.Adapter.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.feidian.george.hzaumooc.Bmob.Bean.AllClass;
import com.feidian.george.hzaumooc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<AllClass> allclass;
    private LayoutInflater layoutInflater;
    public SearchAdapter(Context context, List<AllClass> list)
    {
        this.context = context;
        allclass = list;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllClassAdapter(layoutInflater.inflate(R.layout.a_search_r_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AllClassAdapter)
        {
            ((AllClassAdapter)holder).classname.setText(allclass.get(position).getClass_name());
            ((AllClassAdapter)holder).kind.setText(allclass.get(position).getClass_kind());
            ((AllClassAdapter)holder).teachername.setText(allclass.get(position).getClass_teacher());
            Glide.with(context).load(allclass.get(position).getClass_image()).error(R.mipmap.go_on).into(((AllClassAdapter)holder).roundRectImageView);
        }

    }

    @Override
    public int getItemCount() {
        return allclass.size();
    }
}
