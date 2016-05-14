package com.feidian.george.hzaumooc.Tool;

import android.content.Context;
import android.widget.Toast;

import android.os.Handler;

/**
 * Created by Administrator on 2016/5/13.
 */
public class MyToast {
    private static Toast toast;
    private static Handler handler=new Handler();
    private static Context context;
    private static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            toast.cancel();
        }
    };
    public static void showToast(Context mContext, String text, int duration) {

        handler.removeCallbacks(runnable);
        if (toast != null)
            toast.setText(text);
        else
            toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        handler.postDelayed(runnable, duration);
        toast.show();
    }
}
