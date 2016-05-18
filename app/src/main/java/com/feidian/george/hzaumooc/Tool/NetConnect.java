package com.feidian.george.hzaumooc.Tool;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/5/18.
 */
public class NetConnect {
    public static boolean isNetConnect(Activity act) {
        ConnectivityManager manager = (ConnectivityManager) act.getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE); // 这个类用来查询当前网络状态，通知网络状态变化。
        if (manager == null) {
            return false;
        }
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();//描述目前网络的状态，是否可用
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
