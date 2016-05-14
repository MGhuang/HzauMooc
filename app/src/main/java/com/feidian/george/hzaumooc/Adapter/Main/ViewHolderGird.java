package com.feidian.george.hzaumooc.Adapter.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.NoScrollGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ViewHolderGird extends RecyclerView.ViewHolder {

    @Bind(R.id.main_rg_title)
    TextView title;
    @Bind(R.id.main_rg_grid)
    NoScrollGridView gridView;
    @Bind(R.id.main_rg_more)
    TextView more;
    @Bind(R.id.main_rg_logo)
    ImageView image;
    public ViewHolderGird(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
