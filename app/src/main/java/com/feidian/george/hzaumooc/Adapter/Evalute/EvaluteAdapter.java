package com.feidian.george.hzaumooc.Adapter.Evalute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;


import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/5/14.
 */
public class EvaluteAdapter extends BaseAdapter {
    private List<Evalute> data = new ArrayList<>();
    private Context context;
    private int layout;
    public EvaluteAdapter(List<Evalute> data,Context context,int layout){
        this.data = data;
        this.context = context;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evalute item = data.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.words = (TextView)view.findViewById(R.id.words);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.words.setText(item.getWords());
        return view;
    }
    public class ViewHolder{
        TextView words;
    }
}
