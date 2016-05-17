package com.feidian.george.hzaumooc.Fragment.Download;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.feidian.george.hzaumooc.Activity.DownloadActivity;
import com.feidian.george.hzaumooc.Adapter.Download.DownloadFile;
import com.feidian.george.hzaumooc.Adapter.Download.FileAdapter;
import com.feidian.george.hzaumooc.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DownloadsFragment extends Fragment implements View.OnClickListener{
    private static final int CHANGE = 1;
    private ListView listView = null;
    private List<DownloadFile> list;
    private FileAdapter adapter;
    private RelativeLayout relativeLayout = null;
    private DownloadActivity myActivity = null;
    private String path;
    private int image = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_downloads,null);
        listView = (ListView)view.findViewById(R.id.list_view);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.no_download);
        myActivity = (DownloadActivity)getActivity();
        //设置handler根据ViewPage的滑动更新已下载列表
        myActivity.setHandler(handler);
        //判断是否有下载文件
        isFind();
        adapter = new FileAdapter(list,getActivity(),R.layout.f_downloads_l_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnClickListenerIml());
        //为ListView的列表注册上下文菜单
        super.registerForContextMenu(listView);
        return view;
    }
    public void isFind(){
        if(initialize()){
            relativeLayout.setVisibility(View.VISIBLE);
        }else{
            relativeLayout.setVisibility(View.INVISIBLE);
        }
    }
    //判断是否有下载文件，有则返回true，无则返回false
    public boolean initialize(){
        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/课程中心");
        if(!file.exists()){
            file.mkdirs();
        }
        list = new ArrayList<>();
        File[] xfile = file.listFiles();
        DownloadFile message;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(int i = 0;i<xfile.length;i++){
            String name = xfile[i].getName();
            name = name.substring(name.lastIndexOf(".")+1);
            //根据文件名给予配置不同图片
            if(name.equals("jpg")){
                image = R.mipmap.jpg;
            }else if(name.equals("png")){
                image = R.mipmap.png;
            }else if(name.equals("mp4")||name.equals("3gp")){
                image = R.mipmap.vedio;
            }
            else if(name.equals("ppt"))
            {
                image=R.mipmap.ppt2;
            }
            else if(name.equals("swf"))
            {
                image=R.mipmap.sef;
            }
            else{
                image = R.mipmap.unknow;
            }
            message = new DownloadFile(xfile[i].getName(),String.valueOf(
                    simpleDateFormat.format(new Date(xfile[i].lastModified()))),
                    guiGe(xfile[i].length()),xfile[i].getAbsolutePath(),image);
            list.add(message);
        }
        if(list.size()==0){
            return true;
        }else{
            return false;
        }
    }
    //对文件单位予以处理
    public String guiGe(long length){
        String all;
        float allSize;
        DecimalFormat fnum = new DecimalFormat("##0.0");
        if (length/ 1024 >= 1) {
            allSize =((float) length / 1024);
            all = "k";
            if (allSize / 1024 >= 1) {
                allSize = allSize / 1024;
                all = "m";
                if (allSize / 1024 >= 1) {
                    allSize = allSize / 1024;
                    all = "g";
                }
            }
        } else {
            allSize = length;
            all = "b";
        }
        return String.valueOf(fnum.format(allSize))+all;
    }

    @Override
    public void onClick(View v) {

    }
    //负责删除操作后列表的刷新
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case CHANGE:
                    fresh();
                    break;
                default:break;
            }

        }
    };
    //为下载列表创建上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("操作");
        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "打开");
        menu.add(Menu.NONE,Menu.FIRST+2,2,"删除");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        path = list.get(menuInfo.position).getPath();
        switch (item.getItemId()){
            case Menu.FIRST+1:
                intentTo(path);
                break;
            case Menu.FIRST+2:
                File file = new File(path);
                file.delete();
                fresh();
                break;
            default:break;
        }
        return  false;
    }
    //刷新列表
    public void fresh(){
        isFind();
        initialize();
        adapter = new FileAdapter(list,getActivity(),R.layout.f_downloads_l_item);
        listView.setAdapter(adapter);
    }
    private class OnClickListenerIml implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            path = list.get(position).getPath();
            intentTo(path);
        }
    }
    //获取文件类型
    public String getMIMEType(File file){
        String path = file.getAbsolutePath();
        path = path.substring(path.lastIndexOf(".")+1);
        return path;
    }
    //根据文件类型的不同，选择不同的打开方式
    public void intentTo(String path){
        File file = new File(path);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        if(type.equals("jpg")||type.equals("png")){
            intent.setDataAndType(Uri.fromFile(file),"image/*");
        }else if(type.equals("mp4")||type.equals("3gp")){
            intent.setDataAndType(Uri.fromFile(file),"video/*");
        }else{
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        //跳转
        startActivity(intent);
    }
}
