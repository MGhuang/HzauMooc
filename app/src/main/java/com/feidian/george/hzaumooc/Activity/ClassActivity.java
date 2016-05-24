package com.feidian.george.hzaumooc.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.feidian.george.hzaumooc.Adapter.Class.ClassAdapter;
import com.feidian.george.hzaumooc.Adapter.Main.MainAdapter;
import com.feidian.george.hzaumooc.Bmob.BmobOperate;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;
import com.feidian.george.hzaumooc.Listener.Main.MoreOnClickListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;
import com.feidian.george.hzaumooc.Tool.NetConnect;
import com.feidian.george.hzaumooc.View.ListDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄宇 on 2016/5/11.
 */
public class ClassActivity extends BaseActivity implements UpdateListener,SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_swipe)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.main_list)
    RecyclerView recyclerView;
    @Bind(R.id.class_fab)
    FloatingActionButton floatingActionButton;

    //标记课类
    int kind;

    private ClassAdapter adapter;
    private String[] keys;
    Map<String,ArrayList<?>> map= new HashMap<String,ArrayList<?>>();
    android.os.Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case Main_StaticValue.SUCCESS_GET_DATA:
                    refreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    System.out.println(map.size());
                    break;
                case Main_StaticValue.WRONG_GET_DATA:
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(ClassActivity.this,"亲，请检查网络...",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(ClassActivity.this,"亲，请检查网络...",Toast.LENGTH_SHORT).show();
                    break;
            }
            System.out.println("成功了"+map.size());

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.mipmap.go_back);

        //获得数据包
        Bundle bundle=getIntent().getExtras();
        this.kind=bundle.getInt(MoreOnClickListener.BUNDLE_KIND);

        setRecyclerView();
        setSwipeRefreshLayout();

        setSupportActionBar(toolbar);
    }
    private void checkClass(int kind)    //根据课类查找不同课堂信息
    {
        switch(kind)
        {
            case MainAdapter.CLOUDCLASS_POSITION:  //云课堂数据查找
                toolbar.setTitle(Main_StaticValue.CLOUD_NAME);
                if(keys.length==0)
                {
                    keys= new String[]{Main_StaticValue.CLOUD_NAME};
                    adapter.setKeys(keys);
                }
                BmobOperate.Inist().getCloudClassData(map,this,this);

                break;
            case MainAdapter.PERFECTCLASS_POSITION:  //精品课程数据查找
                toolbar.setTitle(Main_StaticValue.PERFECT_NAME);
                if(keys.length==0)
                {
                    keys = Main_StaticValue.PerfectClass_VALUE;
                    adapter.setKeys(keys);
                }
                BmobOperate.Inist().getPerfectClassData(map,this,this);


                break;
            case MainAdapter.RECOMMEND_POSITION:   //推荐课程查找
                toolbar.setTitle(Main_StaticValue.RECOMMEND_NAME);
                if(keys.length==0)
                {
                    keys =new String[]{Main_StaticValue.PERFECTCLASS_TWO};
                    adapter.setKeys(keys);
                }
                BmobOperate.Inist().getRecommendClassData(map,this,this);
                break;
            default:
                break;
        }
    }
    private void setRecyclerView() // 初始化RecycleView
    {
        adapter=new ClassAdapter(this,map);
        recyclerView.addItemDecoration(new ListDivider());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClassActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setSwipeRefreshLayout()  //设置刷新条
    {
        refreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.BLACK);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED,Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setProgressViewOffset(false, 0, 100);
        netConnect();
    }

    @Override
    public void UpdateOperate() //成功获得数据时调用
    {
        handler.sendEmptyMessage(Main_StaticValue.SUCCESS_GET_DATA);
    }

    @Override
    public void errorToast()   //获得数据失败时调用
    {
        handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }

    @Override
    public void onRefresh()   //用户刷新
    {
        netConnect();
    }
    private void netConnect()  //判断网络
    {
        if(NetConnect.isNetConnect(this))
        {
            checkClass(kind);
        }
        else
            handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)   //toorbar上的按钮监听
    {
        int id=item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return false;
    }
    private void checkKeys()
    {
        switch(kind)
        {
            case MainAdapter.CLOUDCLASS_POSITION:  //云课堂数据查找
                keys= new String[]{Main_StaticValue.CLOUD_NAME};
                break;
            case MainAdapter.PERFECTCLASS_POSITION:  //精品课程数据查找
                keys = Main_StaticValue.PerfectClass_VALUE;
                break;
            case MainAdapter.RECOMMEND_POSITION:   //推荐课程查找
                keys =new String[]{Main_StaticValue.PERFECTCLASS_TWO};
                break;
            default:
                break;
        }
    }
}
