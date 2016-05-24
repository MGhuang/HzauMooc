package com.feidian.george.hzaumooc.Bmob.Query;

import android.content.Context;
import android.widget.Toast;

import com.feidian.george.hzaumooc.Bmob.Bean.AllClass;
import com.feidian.george.hzaumooc.Bmob.Bean.CloudClass;
import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;
import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.Bmob.Bean.PageViewResource;
import com.feidian.george.hzaumooc.Bmob.Bean.PerfectClass;
import com.feidian.george.hzaumooc.Bmob.BmobOperate;
import com.feidian.george.hzaumooc.Exception.GetDataException;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyBmobQuery {


    public static void findSearch(final String key, final String value, final List<AllClass> list, final Context context, final UpdateListener listener)
    {
        new Thread(){
            @Override
            public void run() {
                AllClassQuery(key,value,list,context,listener);
            }
        }.start();
    }

    public static void findEvlute(final String key, final String value, final List<Evalute> list, final Context context, final UpdateListener listener)
    {
        new Thread()
        {
            @Override
            public void run() {
                EvaluteQuery(key,value,list,context,listener);

            }
        }.start();
    }
    /*
    *用于查找主界面的数据
    * viewpage 图片轮播
    * 热门推荐
    * 精品课程
    * 云课堂
    * 热门点击
     */
    public static void findMainView(final Map<String,ArrayList<?>> map, final Context context, final UpdateListener listener)
    {
        new Thread()
        {
            private String key=Main_StaticValue.MAINVIEW_MAINVALUE_KEY;
            private String[] value=Main_StaticValue.MAINVIEW_MAINVALUE_VALUE;
            @Override
            public void run() {
                int i=1;
                for(String s:value)
                {
                    MainValueDataQuery(key,s,map,context);
                }
                //获取图片轮播
                PageViewResourceDataQuery("",Main_StaticValue.PAGE_NAME,map,context);
                while(map.size()!=5){}
                listener.UpdateOperate();
            }
        }.start();
    }



    public static void findPerfectClass(final Map<String,ArrayList<?>> map,final Context context,final UpdateListener listener)
    {
        new Thread()
        {
            @Override
            public void run() {
                for(String value:Main_StaticValue.PerfectClass_VALUE)
                {
                    PerfectClassDataQuery(Main_StaticValue.PerfectClass_KEY,value,map,context);
                }
                while(map.size()!=Main_StaticValue.PerfectClass_VALUE.length);
                listener.UpdateOperate();
            }
        }.start();
    }

    public static void findCloudClass(final Map<String,ArrayList<?>> map, final Context context, final UpdateListener listener)
    {
        new Thread()
        {
            @Override
            public void run() {
                CloudClassDataQuery(null,null,map,context);
                while(map.size()!=1);
                listener.UpdateOperate();
            }
        }.start();
    }

    public static  void findRecommedClass(final Map<String,ArrayList<?>> map, final Context context, final UpdateListener listener)
    {
        new Thread()
        {
            @Override
            public void run() {
                PerfectClassDataQuery(Main_StaticValue.PerfectClass_KEY,Main_StaticValue.PERFECTCLASS_TWO,map,context);
                while(map.size()!=1);
                listener.UpdateOperate();
            }
        }.start();
    }

    private static void  MainValueDataQuery(String key,final String value,final Map<String,ArrayList<?>> map,Context context)
    {
        cn.bmob.v3.BmobQuery<MainValue> query=new cn.bmob.v3.BmobQuery<MainValue>();
        query.addWhereMatches(key,value);
        query.order("-updatedAt");
        query.findObjects(context, new FindListener<MainValue>() {
            @Override
            public void onSuccess(List<MainValue> list) {
                /*for(MainValue m:list)
                {
                    System.out.println(m.getClass_kind());
                }*/
                map.put(value,new ArrayList<MainValue>(list));
                System.out.println("MainValue"+"成功");
                System.out.println(map.size());
            }
            @Override
            public void onError(int i, String s) {
                try{
                    throw new GetDataException(s+"获取"+value+"失败"+"MainValueDataQuery");
                }catch (GetDataException e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage()+s);
                }

            }
        });
    }
    private static void PerfectClassDataQuery(String key,final String value,final Map<String,ArrayList<?>> map,Context context)
    {
        BmobQuery<PerfectClass> query=new BmobQuery<PerfectClass>();
        query.addWhereMatches(key,value);
        query.order("-updatedAt");
        System.out.println("以上成功了1");
        query.findObjects(context, new FindListener<PerfectClass>() {
            @Override
            public void onSuccess(List<PerfectClass> list) {
                    map.put(value,new ArrayList<PerfectClass>(list));
                    System.out.println("PerfectClass"+"成功");
                    System.out.println(map.size());
            }
            @Override
            public void onError(int i, String s) {
                try{
                    throw new GetDataException(s+"获取"+value+"失败"+"PerfectClassDataQuery");
                }catch (GetDataException e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage()+s);
                }

            }
        });
    }
    static void PageViewResourceDataQuery(String key,final String value,final Map<String,ArrayList<?>> map,Context context) {
        cn.bmob.v3.BmobQuery<PageViewResource> query = new cn.bmob.v3.BmobQuery<PageViewResource>();
        query.findObjects(context, new FindListener<PageViewResource>() {
            @Override
            public void onSuccess(List<PageViewResource> list) {
                for(PageViewResource p:list)
                {
                    System.out.println(p.getImageUrl());
                }
                map.put(value, new ArrayList<PageViewResource>(list));
                System.out.println("PageViewResource"+"成功");
                System.out.println(map.size());
            }

            @Override
            public void onError(int i, String s) {
                try {
                    throw new GetDataException(s + "获取" + value + "失败" + "PageViewResourceDataQuery");
                } catch (GetDataException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage()+s);
                }

            }
        });
    }
    static void CloudClassDataQuery(String key,final String value,final Map<String,ArrayList<?>> map,Context context)
    {
        cn.bmob.v3.BmobQuery<CloudClass> query = new cn.bmob.v3.BmobQuery<CloudClass>();
        query.addWhereMatches(key,value);
        query.order("-updatedAt");
        query.findObjects(context, new FindListener<CloudClass>() {
            @Override
            public void onSuccess(List<CloudClass> list) {
                map.put(Main_StaticValue.CLOUD_NAME, new ArrayList<CloudClass>(list));
                System.out.println("CloudClass"+"成功");
                System.out.println(map.size());
            }

            @Override
            public void onError(int i, String s) {
                try {
                    throw new GetDataException(s + "获取" + value + "失败" + "CloudClassDataQuery");
                } catch (GetDataException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage()+s);
                }

            }
        });

    }
    private static void EvaluteQuery(String key, final String value, final List<Evalute> elist, Context context, final UpdateListener listener)
    {
        cn.bmob.v3.BmobQuery<Evalute> bmobQuery=new cn.bmob.v3.BmobQuery<Evalute>();
        bmobQuery.addWhereMatches(key,value);
        bmobQuery.order("-updatedAt");
        bmobQuery.setLimit(10);
        bmobQuery.findObjects(context, new FindListener<Evalute>() {
            @Override
            public void onSuccess(List<Evalute> list) {
                elist.addAll(list);
                listener.UpdateOperate();
            }

            @Override
            public void onError(int i, String s) {
                listener.errorToast();
            }
        });
    }
    private static void AllClassQuery(String key, final String value, final List<AllClass> alist, Context context, final UpdateListener listener)
    {
        BmobQuery<AllClass> bmobQuery=new BmobQuery<AllClass>();
        bmobQuery.addWhereEqualTo(key,value);
        bmobQuery.order("-updatedAt");
        bmobQuery.setLimit(10);
        bmobQuery.findObjects(context, new FindListener<AllClass>() {
            @Override
            public void onSuccess(List<AllClass> list) {
                alist.clear();
                alist.addAll(list);
                listener.UpdateOperate();
            }

            @Override
            public void onError(int i, String s) {
                alist.clear();
                listener.errorToast();
            }
        });
    }
}
