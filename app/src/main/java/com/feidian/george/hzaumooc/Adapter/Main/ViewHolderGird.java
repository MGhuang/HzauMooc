package com.feidian.george.hzaumooc.Adapter.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.Listener.Main.ItemOnClickListener;
import com.feidian.george.hzaumooc.Listener.Main.MoreOnClickListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.View.NoScrollGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
class ViewHolderGird extends RecyclerView.ViewHolder {

    private ItemOnClickListener itemOnClickListener;
    private MoreOnClickListener moreOnClickListener;
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
        image.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    public void setListener(ArrayList<MainValue> list, int kind, Context context)
    {
        setItemOnClickListener(list,context);
        setMoreOnClickListener(kind,context);
        gridView.setOnItemClickListener(itemOnClickListener);
        more.setOnClickListener(moreOnClickListener);
    }
    private void setItemOnClickListener(ArrayList<MainValue> list, Context context)
    {
        if(itemOnClickListener == null)
        {
            itemOnClickListener = new ItemOnClickListener(list,context);
        }
        else
        {
            itemOnClickListener.setList(list);
        }
    }
    private void setMoreOnClickListener(int kind, Context context)
    {
        if(moreOnClickListener == null)
        {
            moreOnClickListener = new MoreOnClickListener(kind,context);
        }
        else
        {
            moreOnClickListener.setKind(kind);
        }
    }
}
