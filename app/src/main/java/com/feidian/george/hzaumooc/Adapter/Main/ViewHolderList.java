package com.feidian.george.hzaumooc.Adapter.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.NoScrollListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ViewHolderList extends RecyclerView.ViewHolder{

    @Bind(R.id.main_rl_logo)
    ImageView logo;
    @Bind(R.id.main_rl_title)
    TextView title;
    @Bind(R.id.main_rl_list)
    NoScrollListView listView;

    public ViewHolderList(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
