package com.feidian.george.hzaumooc.Bmob.Bean;

import com.feidian.george.hzaumooc.Interface.Main.GetValued;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/5/13.
 */
public class CloudClass extends BmobObject implements GetValued{
    private String class_name;
    private String class_image;
    private String class_teacher;
    public String getClass_name()
    {
        return class_name;
    }
    public String getClass_image()
    {
        return class_image;
    }
    public String getClass_teacher()
    {
        return class_teacher;
    }

    @Override
    public String toString() {
        return new String(class_name+"   "+class_teacher);
    }

    @Override
    public String getValued_class_name() {
        return class_name;
    }

    @Override
    public String getValued_class_Image() {
        return class_image;
    }

    @Override
    public String getValued_class_kind() {
        return Main_StaticValue.CLOUD_NAME;
    }

    @Override
    public String getValued_class_id() {
        return getObjectId();
    }

    @Override
    public String getValued_class_teacher() {
        return class_teacher;
    }
}
