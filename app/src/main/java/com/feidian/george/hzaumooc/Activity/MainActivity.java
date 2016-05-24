package com.feidian.george.hzaumooc.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.feidian.george.hzaumooc.Adapter.Main.MainAdapter;
import com.feidian.george.hzaumooc.Bmob.BmobOperate;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.AppTool;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;
import com.feidian.george.hzaumooc.Tool.NetConnect;
import com.feidian.george.hzaumooc.View.ListDivider;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,UpdateListener,
        SwipeRefreshLayout.OnRefreshListener{


    MainAdapter adapter;
    SearchView mSearchView;
    Map<String,ArrayList<?>> map =new HashMap<String, ArrayList<?>>(MainAdapter.ITEM_ACCOUT+2);
    @Bind(R.id.main_list)
    RecyclerView recyclerView;
    @Bind(R.id.main_swipe)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case Main_StaticValue.SUCCESS_GET_DATA:
                    refreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    System.out.println(map.size());
                    break;
                case Main_StaticValue.WRONG_GET_DATA:
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this,"亲，请检查网络哦...",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this,"亲，请检查网络哦...",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.main_menu_search:
                        Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });*/
        ButterKnife.bind(this);
        //setData();
        Bmob.initialize(this,"bdc6b1e1572437dda9938ac54b702a5b");



        setRecyclerView();
        setSwipeRefreshLayout();

        //设置侧边拉出框
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {   //返回键监听
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_personal) {
            Intent intent = new Intent(MainActivity.this,PersonActivity.class);
            startActivity(intent);
        } else if (id == R.id.main_download) {
              Intent intent = new Intent(MainActivity.this,DownloadActivity.class);
              startActivity(intent);
        } else if (id == R.id.main_history) {

        } else if (id == R.id.main_set) {

        } else if (id == R.id.main_share) {

        } else if (id == R.id.main_send) {
               Intent intent = new Intent(MainActivity.this,SuggestActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setRecyclerView()  //初始化RecyclerView
    {
        adapter=new MainAdapter(this,map);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ListDivider());
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
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
    private void setData()
    {
        //判断有无sd卡
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            AppTool.isHasSD=true;
            File file=new File(Environment.getExternalStorageDirectory().getPath()+"/hzaumooc/mainresource.bin");
            if(!file.exists())
            {
                file.mkdirs();
            }
        }
    }
    @Override
    public void onRefresh() {

        netConnect();
    }

    @Override
    public void UpdateOperate() {
        handler.sendEmptyMessage(Main_StaticValue.SUCCESS_GET_DATA);
    }

    @Override
    public void errorToast() {
        handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }

    private void getData()
    {
        BmobOperate.Inist().getMainData(map,this,this);
    }
    private void netConnect()
    {
        if(NetConnect.isNetConnect(this))
        {
            getData();
        }
        else
            handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
}
