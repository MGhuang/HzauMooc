package com.feidian.george.hzaumooc.Bmob;

import android.content.Context;

import com.feidian.george.hzaumooc.Bmob.Query.BmobQuery;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;

import java.util.ArrayList;
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
        BmobQuery.findMainView(map,context,listener);
    }

}
