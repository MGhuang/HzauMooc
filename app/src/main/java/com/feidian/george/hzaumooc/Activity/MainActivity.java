package com.feidian.george.hzaumooc.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private final static int SUCCESS_GET_MAINDATA= 9;
    MainAdapter adapter;
    Map<String,ArrayList<?>> map =new HashMap<String, ArrayList<?>>(MainAdapter.ITEM_ACCOUT+2);
    @Bind(R.id.main_list)
    RecyclerView recyclerView;
    @Bind(R.id.main_swipe)
    SwipeRefreshLayout refreshLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case SUCCESS_GET_MAINDATA:
                    refreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    System.out.println(map.size());
                    break;
                default:
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this,"网络太差",Toast.LENGTH_SHORT).show();
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
        ButterKnife.bind(this);
        //setData();
        Bmob.initialize(this,"bdc6b1e1572437dda9938ac54b702a5b");
        setRecyclerView();
        setSwipeRefreshLayout();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
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
    private void setRecyclerView()
    {
        adapter=new MainAdapter(this,map);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ListDivider());
        recyclerView.setAdapter(adapter);
    }
    private void setSwipeRefreshLayout()  //设置刷新条
    {
        refreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.BLACK);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED,Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setProgressViewOffset(false, 0, 100);
        refreshLayout.setRefreshing(true);
        getData();
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
        getData();
    }

    @Override
    public void UpdateOperate() {
        handler.sendEmptyMessage(9);
    }
    private void getData()
    {
        BmobOperate.Inist().getMainData(map,this,this);
    }
}
