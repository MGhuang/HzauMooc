package com.feidian.george.hzaumooc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.feidian.george.hzaumooc.Adapter.Search.SearchAdapter;
import com.feidian.george.hzaumooc.Adapter.Search.SearchListAdapter;
import com.feidian.george.hzaumooc.Bmob.Bean.AllClass;
import com.feidian.george.hzaumooc.Bmob.BmobOperate;
import com.feidian.george.hzaumooc.Bmob.Upload.BmobUpload;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;
import com.feidian.george.hzaumooc.Tool.NetConnect;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/19.
 */
public class SearchActivity extends BaseActivity implements UpdateListener{
    @Bind(R.id.search_s)
    SearchView searchView;
    @Bind(R.id.search_list)
    ListView listView;

    private SearchListAdapter searchAdapter;
    private List<AllClass> allClassList;

    android.os.Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case Main_StaticValue.SUCCESS_GET_DATA:
                    if(allClassList.size()==0)
                    {
                        Toast.makeText(SearchActivity.this,"亲，没找到哦...",Toast.LENGTH_SHORT).show();
                    }
                    searchAdapter.notifyDataSetChanged();
                    break;
                case Main_StaticValue.WRONG_GET_DATA:
                    Toast.makeText(SearchActivity.this,"亲，请检查网络哦...",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(SearchActivity.this,"亲，请检查网络哦...",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        allClassList =new ArrayList<AllClass>();
        searchAdapter = new SearchListAdapter(allClassList,this);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("搜索");
        toolbar.setNavigationIcon(R.mipmap.go_back);
        setSupportActionBar(toolbar);

        setListView();

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BmobOperate.Inist().getSearchData("class_name",query,allClassList,SearchActivity.this,SearchActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return false;
            }
        });

    }
    private void setListView()
    {
        listView.setAdapter(searchAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SearchActivity.this,DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(DetailActivity.BUNDLE_DETAIL_CLASSNAME,allClassList.get(position).getClass_name());
                bundle.putString(DetailActivity.BUNDLE_DETAIL_CLASSKIND,allClassList.get(position).getClass_kind());
                bundle.putString(DetailActivity.BUNDLE_DETAIL_TEACHER,allClassList.get(position).getClass_teacher());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
    private void netConnect()
    {
        if(NetConnect.isNetConnect(this))
        {
            setListView();
        }
        else
            handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }

    @Override
    public void UpdateOperate() {
        handler.sendEmptyMessage(Main_StaticValue.SUCCESS_GET_DATA);
    }

    @Override
    public void errorToast() {
        handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//toorbar监听
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
}
