package com.feidian.george.hzaumooc.Adapter.Search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.RoundRectImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/20.
 */
class AllClassAdapter extends RecyclerView.ViewHolder{
    @Bind(R.id.search_r_item_classname)
    TextView classname;
    @Bind(R.id.search_r_item_image)
    RoundRectImageView roundRectImageView;
    @Bind(R.id.search_r_item_kind)
    TextView kind;
    @Bind(R.id.search_r_item_teachername)
    TextView teachername;

    public AllClassAdapter(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
