package com.feidian.george.hzaumooc.Adapter.Detail.VideoList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/16.
 */
class ViewHolderCard extends RecyclerView.ViewHolder{
    @Bind(R.id.detail_rc_image)
    ImageView image;
    @Bind(R.id.detail_rc_name)
    TextView name;
    @Bind(R.id.detail_rc_teacher)
    TextView tname;
    public ViewHolderCard(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
