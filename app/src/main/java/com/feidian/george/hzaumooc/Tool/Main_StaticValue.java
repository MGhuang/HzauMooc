package com.feidian.george.hzaumooc.Tool;

import com.feidian.george.hzaumooc.R;

/**
 * Created by Administrator on 2016/5/13.
 */
public class Main_StaticValue {
    public final static int SUCCESS_GET_DATA= 9;  //用于设定实现UpdateListener的handler  表示是否获得数据成功
    public final static int WRONG_GET_DATA=0;

    public static final int DIFFERENCE=1; //记录主布局各项与MAIN_TITLE_IMAGE的对应差
    public static final String PAGE_NAME="图片轮播";
    public static final String RECOMMEND_NAME="官方推荐";
    public static final String PERFECT_NAME="精品课程";
    public static final String CLOUD_NAME="云课堂";
    public static final String HOT_NAME="热门点击";
    public static final int[] MAIN_TITLE_IMAGE={R.mipmap.like,R.mipmap.perfect,R.mipmap.cloud,R.mipmap.hot};


    public static final String PERFECTCLASS_ONE="国家级精品视频公开课";
    public static final String PERFECTCLASS_TWO="国家级精品资源共享课";
    public static final String PERFECTCLASS_THREE="省级精品课程";


    //BmobQuery   findMainView用到
    public static final String MAINVIEW_MAINVALUE_KEY="class_kind";
    public static final String[] MAINVIEW_MAINVALUE_VALUE={Main_StaticValue.RECOMMEND_NAME,Main_StaticValue.PERFECT_NAME
            ,Main_StaticValue.HOT_NAME,Main_StaticValue.CLOUD_NAME};

    //BmobQuery  findPerfectClass用到
    public static final String PerfectClass_KEY="class_kind";
    public static final String[] PerfectClass_VALUE={PERFECTCLASS_ONE,PERFECTCLASS_TWO,PERFECTCLASS_THREE};

}
