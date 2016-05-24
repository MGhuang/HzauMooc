package com.feidian.george.hzaumooc.Adapter.Detail.Resource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.feidian.george.hzaumooc.Adapter.Evalute.EvaluteAdapter;
import com.feidian.george.hzaumooc.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class GridAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    public GridAdapter(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.f_detail_r_timelayout_g_items,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.item= (ImageView) convertView.findViewById(R.id.detail_rg_image);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder
    {
        public ImageView item;
    }
}
