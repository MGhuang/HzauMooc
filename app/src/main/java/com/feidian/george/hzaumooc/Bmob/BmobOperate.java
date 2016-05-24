package com.feidian.george.hzaumooc.Bmob;

import android.content.Context;

import com.feidian.george.hzaumooc.Bmob.Bean.AllClass;
import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;
import com.feidian.george.hzaumooc.Bmob.Query.MyBmobQuery;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/12.
 */
public class BmobOperate {
    private static BmobOperate bmobOperate;
    static
    {
        bmobOperate = new BmobOperate();
    }
    private BmobOperate(){}
    public static BmobOperate Inist()
    {
        return bmobOperate;
    }
    public void getMainData(Map<String,ArrayList<?>> map, Context context,UpdateListener listener)
    {
        MyBmobQuery.findMainView(map,context,listener);
    }
    public void getPerfectClassData(Map<String,ArrayList<?>> map, Context context,UpdateListener listener)
    {
        MyBmobQuery.findPerfectClass( map, context,listener);
    }
    public void getCloudClassData(Map<String,ArrayList<?>> map, Context context,UpdateListener listener)
    {
        MyBmobQuery.findCloudClass(map,context,listener);

    }
    public void getRecommendClassData(Map<String,ArrayList<?>> map, Context context,UpdateListener listener)
    {
        MyBmobQuery.findRecommedClass(map,context,listener);

    }
    public void getEvaluteDate(String key,String value,List<Evalute> list,Context context,UpdateListener listener)
    {
        MyBmobQuery.findEvlute(key,value,list,context,listener);
    }
    public void getSearchData(String key, String value, List<AllClass> list, Context context, UpdateListener listener)
    {
        MyBmobQuery.findSearch(key,value,list,context,listener);
    }

}
