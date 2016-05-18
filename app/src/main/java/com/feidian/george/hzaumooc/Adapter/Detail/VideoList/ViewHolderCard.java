package com.feidian.george.hzaumooc.Adapter.Detail.VideoList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.george.hzaumooc.Activity.EvaluteActivity;
import com.feidian.george.hzaumooc.Adapter.Evalute.EvaluteAdapter;
import com.feidian.george.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/16.
 */
class ViewHolderCard extends RecyclerView.ViewHolder implements View.OnClickListener{

    String coursename;
    String url;
    Context context;


    @Bind(R.id.detail_rc_cv_item)
    CardView cardView;
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
    public void setOnClickListener(String coursename, String url, Context context)
    {
        this.coursename=coursename;
        this.url=url;
        this.context=context;
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, EvaluteActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(EvaluteActivity.BUNDLE_CLASSNAME,coursename);
        bundle.putString(EvaluteActivity.BUNDLE_URL,url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
