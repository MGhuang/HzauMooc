package com.feidian.george.hzaumooc.Adapter.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.george.hzaumooc.Bmob.Bean.AllClass;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.RoundRectImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/20.
 */
public class SearchListAdapter extends BaseAdapter{
    private List<AllClass> allclass;
    private Context context;
    private LayoutInflater layoutInflater;
    public SearchListAdapter(List<AllClass> list,Context context)
    {
        this.context=context;
        allclass = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return allclass.size();
    }

    @Override
    public Object getItem(int position) {
        return allclass.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListHolder listHolder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.a_search_r_item,parent,false);
            listHolder=new ListHolder();
            listHolder.classname=(TextView)convertView.findViewById(R.id.search_r_item_classname);
            listHolder.roundRectImageView=(RoundRectImageView) convertView.findViewById(R.id.search_r_item_image);
            listHolder.kind=(TextView)convertView.findViewById(R.id.search_r_item_kind);
            listHolder.teachername=(TextView)convertView.findViewById(R.id.search_r_item_teachername);
            convertView.setTag(listHolder);
        }
        else
        {
            listHolder= (ListHolder) convertView.getTag();
        }
        listHolder.classname.setText(allclass.get(position).getClass_name());
        listHolder.kind.setText(allclass.get(position).getClass_kind());
        listHolder.teachername.setText(allclass.get(position).getClass_teacher());
        Glide.with(context).load(allclass.get(position).getClass_image())
                .error(R.mipmap.go_on)
                .into(listHolder.roundRectImageView);
        return convertView;
    }
    class ListHolder{
        TextView classname;
        RoundRectImageView roundRectImageView;
        TextView kind;
        TextView teachername;
    }
}
