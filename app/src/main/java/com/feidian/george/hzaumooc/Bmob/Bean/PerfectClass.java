package com.feidian.george.hzaumooc.Bmob.Bean;

import com.feidian.george.hzaumooc.Interface.Main.GetValued;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/5/12.
 */
public class PerfectClass extends BmobObject implements Serializable ,GetValued{
    private String classname;
    private String class_image;
    private String class_web;
    private String class_teacher;
    private BmobFile Image;
    private String class_kind;
    public void setClassname(String classname)
    {
        this.classname=classname;
    }
    public String getClassname()
    {
        return classname;
    }
    public void setClass_image(String class_image)
    {
        this.class_image=class_image;
    }
    public String getClass_image()
    {
        return class_image;
    }
    public void setClass_web (String class_web)
    {
        this.class_web=class_web;
    }
    public String getClass_web ()
    {
        return class_web;
    }
    public void setClass_teacher (String class_teacher)
    {
        this.class_teacher=class_teacher;
    }
    public String getClass_teacher ()
    {
        return class_teacher;
    }
    public BmobFile getImage()
    {
        return Image;
    }
    public void setImage(BmobFile Image)
    {
        this.Image=Image;
    }
    public void setClass_kind(String kind){this.class_kind=kind;}
    public String getClass_kind(){return class_kind;}
    @Override
    public String toString() {
        return new String(classname+"   "+class_teacher);
    }

    @Override
    public String getValued_class_name() {
        return classname;
    }

    @Override
    public String getValued_class_Image() {
        return class_image;
    }

    @Override
    public String getValued_class_kind() {
        return class_kind;
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
