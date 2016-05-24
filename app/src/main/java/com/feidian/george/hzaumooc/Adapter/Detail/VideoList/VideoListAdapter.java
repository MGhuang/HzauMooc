package com.feidian.george.hzaumooc.Adapter.Detail.VideoList;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
   // private List<String> videoImage_List;
    private List<String> videoName_List;
    private List<String> videoTName_List;
    private List<String> video;

    //测试，暂缺数据
    public VideoListAdapter(Context context,String class_name,String class_teacher)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        //videoImage_List=new ArrayList<>(15);
        videoName_List=new ArrayList<>(15);
        videoTName_List=new ArrayList<>(15);
        video= Arrays.asList(context.getResources().getStringArray(R.array.static_video));
        for(int i=1;i<=10;i++)
        {
            videoTName_List.add(class_teacher);
            videoName_List.add(class_name);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderCard(layoutInflater.inflate(R.layout.f_detail_r_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderCard)
        {
            ((ViewHolderCard)holder).image.setImageResource(R.mipmap.school);
            ((ViewHolderCard)holder).name.setText(videoName_List.get(position));
            ((ViewHolderCard)holder).tname.setText(videoTName_List.get(position));
            System.out.println("老师名字"+videoTName_List.get(position));
            ((ViewHolderCard)holder).setOnClickListener(videoName_List.get(position),
                    video.get(position),
                    context);
        }

    }

    @Override
    public int getItemCount() {
        return videoName_List.size();
    }
}
