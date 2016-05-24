package com.feidian.george.hzaumooc.Adapter.Main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.george.hzaumooc.Bmob.Bean.CloudClass;
import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.RoundRectImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
public class GridAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<?> list;
    private  int kind;
    public GridAdapter(Context context,ArrayList<?> list,int position)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.list=list;
        kind=position;
    }
    class ViewHolder
    {
        @Bind(R.id.main_rg_item_image)
        RoundRectImageView imageView;
        @Bind(R.id.main_rg_item_title)
        TextView textView;
        public ViewHolder(View v)
        {
            ButterKnife.bind(this, v);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            textView.setGravity(Gravity.CENTER);
        }
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.a_main_r_gridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch(kind)
        {
            case MainAdapter.CLOUDCLASS_POSITION:
            case MainAdapter.PERFECTCLASS_POSITION:
            case MainAdapter.RECOMMEND_POSITION:
                ArrayList<MainValue> mainValues = (ArrayList<MainValue>) list;
                holder.textView.setText(mainValues.get(position).getClass_name());
                Glide.with(context).load(mainValues.get(position).getClass_image()).error(R.mipmap.go_on).into(holder.imageView);
                break;
            default:
                holder.textView.setText("加载中...");
                holder.imageView.setImageResource(R.mipmap.go_on);
                break;
        }
        return convertView;
    }
}
