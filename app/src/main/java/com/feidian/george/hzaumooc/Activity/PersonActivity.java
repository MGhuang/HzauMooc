package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;


import com.feidian.george.hzaumooc.Adapter.Person.More;
import com.feidian.george.hzaumooc.Adapter.Person.MoreAdapter;
import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 2016/4/21.
 */
public class PersonActivity extends Activity{
    private List<More> data = new ArrayList<>();

    @Bind(R.id.person_listview)
    ListView listView = null;

    private MoreAdapter adapter;
    private String[] name = {"消息提醒","历史纪录","反馈建议","关于我们","退出登录"};
    private int[] image = {R.mipmap.news,R.mipmap.record,R.mipmap.suggest,R.mipmap.about,R.mipmap.out};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        init();
    }
    //初始化列表
    public void init(){
        for(int i = 0;i<name.length;i++){
            data.add(new More(name[i],image[i]));
        }
        adapter = new MoreAdapter(R.layout.a_person_l_item,data,this);
        listView.setAdapter(adapter);
    }
}
