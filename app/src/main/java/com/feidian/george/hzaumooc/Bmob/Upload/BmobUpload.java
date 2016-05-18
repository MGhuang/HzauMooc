package com.feidian.george.hzaumooc.Bmob.Upload;

import android.content.Context;

import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/5/18.
 */
public class BmobUpload {
    public static final void uploadEvalute(final Evalute evalute, final Context context)
    {
        new Thread(){
            @Override
            public void run() {
                evalute.save(context, new SaveListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }
        }.start();

    }
}
