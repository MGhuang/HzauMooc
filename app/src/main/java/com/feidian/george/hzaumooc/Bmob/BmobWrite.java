package com.feidian.george.hzaumooc.Bmob;

import android.content.Context;

import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;
import com.feidian.george.hzaumooc.Bmob.Upload.BmobUpload;

/**
 * Created by Administrator on 2016/5/18.
 */
public class BmobWrite {
    private static BmobWrite bmobWrite;
    static
    {
        bmobWrite=new BmobWrite();
    }
    public static BmobWrite Inist(){return bmobWrite;}
    public void uploadEvaluted(Evalute evalute, Context context)
    {
        BmobUpload.uploadEvalute(evalute,context);

    }
}
