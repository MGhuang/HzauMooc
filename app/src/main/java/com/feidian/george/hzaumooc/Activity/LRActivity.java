package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.LoginUtil;

import cn.bmob.v3.Bmob;


/**
 * Created by lenovo on 2016/5/20.
 */
public class LRActivity extends Activity{
    private Fragment[] mFragments;
    private android.app.FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private final static int LOGIN = 0;
    private final static int REGIST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.a_lr);
        Bmob.initialize(this,"bdc6b1e1572437dda9938ac54b702a5b");
        LoginUtil.handler = handler;
        mFragments = new Fragment[2];
        fragmentManager = getFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.login);
        mFragments[1] = fragmentManager.findFragmentById(R.id.regist);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]);
        fragmentTransaction.show(mFragments[0]).commit();
    }
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
           switch (msg.what){
               case LOGIN:
                   fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]);
                   fragmentTransaction.show(mFragments[0]).commit();
                   break;
               case REGIST:
                   fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]);
                   fragmentTransaction.show(mFragments[1]).commit();
                   break;
               default:break;
           }
        }
    };
}
