package com.feidian.george.hzaumooc.Listener.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.feidian.george.hzaumooc.Activity.ClassActivity;
import com.feidian.george.hzaumooc.Adapter.Main.MainAdapter;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;


/**
 * Created by Administrator on 2016/5/14.
 */
public class MoreOnClickListener implements View.OnClickListener{
    public static final String BUNDLE_KIND="kind";
    private int kind; //用于和 Main_StaticValue比对
    private Context context;
    public MoreOnClickListener(int kind, Context context)
    {
        this.kind=kind;
        this.context=context;
    }
    @Override
    public void onClick(View v) {
        System.out.println(kind);
        Intent intent=new Intent(context, ClassActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("kind",kind);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public void setKind(int Kind)
    {
        kind=Kind;
    }
}
