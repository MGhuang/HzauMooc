package com.feidian.george.hzaumooc.Adapter.Suggestion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.feidian.george.hzaumooc.R;

import java.util.List;

public class TalkAdapter extends ArrayAdapter<Talk> {
    private int resouceId;


    public TalkAdapter(Context context, int resource, List<Talk> objects) {
        super(context, resource, objects);
        resouceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Talk msg = getItem(position);
        ViewHolder holder;
        View view;
        if(convertView==null){
            view=View.inflate(getContext(), resouceId, null);
            holder=new ViewHolder();
            holder.left_layout=(LinearLayout) view.findViewById(R.id.left_layout);
            holder.right_layout=(LinearLayout) view.findViewById(R.id.right_layout);
            holder.left_msg=(TextView) view.findViewById(R.id.left_msg);
            holder.right_msg=(TextView) view.findViewById(R.id.right_msg);
            view.setTag(holder);
        }else{
            view=convertView;
            holder = (ViewHolder) view.getTag();

        }
        if(msg.getType()==Talk.TYPE_RECEIVED){
            //收到消息，显示左边的消息布局，将右边的隐藏
            holder.left_layout.setVisibility(View.VISIBLE);
            holder.right_layout.setVisibility(View.GONE);
            holder.left_msg.setText(msg.getContent());
        }else if(msg.getType()==Talk.TYPE_SENT){
            holder.left_layout.setVisibility(View.GONE);
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.right_msg.setText(msg.getContent());
        }

        return view;



    }
    class ViewHolder{
        LinearLayout left_layout,right_layout;
        TextView left_msg,right_msg;
    }


}











