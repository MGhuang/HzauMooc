package com.feidian.george.hzaumooc.Listener.Main;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.Interface.Main.GetValued;

import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ItemOnClickListener implements AdapterView.OnItemClickListener {
    private List<?>  list;
    private Context context;
    public ItemOnClickListener(List<?> list, Context context)
    {
        this.list=list;
        this.context=context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(((GetValued)list.get(position)).getValued_class_name());

    }
    public void setList(List<?> list)
    {
        this.list=list;
    }
}
