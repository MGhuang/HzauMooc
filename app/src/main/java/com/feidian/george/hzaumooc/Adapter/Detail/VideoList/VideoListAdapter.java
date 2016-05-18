package com.feidian.george.hzaumooc.Adapter.Detail.VideoList;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feidian.george.hzaumooc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> videoImage_List;
    private List<String> videoName_List;
    private List<String> videoTName_List;
    public VideoListAdapter(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
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
            ((ViewHolderCard)holder).name.setText("魅力汉语");
            ((ViewHolderCard)holder).tname.setText("刘冠成");
            ((ViewHolderCard)holder).setOnClickListener("魅力汉语",
                    "http://211.69.141.12:1221/upload/b87e1021-8fe4-4d8c-abce-21146617e942.mp4",
                    context);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
