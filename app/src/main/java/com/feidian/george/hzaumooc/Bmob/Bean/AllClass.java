package com.feidian.george.hzaumooc.Bmob.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/5/20.
 */
public class AllClass extends BmobObject{
    private String class_name;
    private String class_image;
    private String class_kind;
    private String class_teacher;

    public String getClass_name()
    {
        return class_name;
    }
    public String getClass_kind()
    {
        return class_kind;
    }
    public  String getClass_image()
    {
        return class_image;
    }
    public String getClass_teacher()
    {
        return class_teacher;
    }
}
