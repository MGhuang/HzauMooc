package com.feidian.george.hzaumooc.Bmob.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/5/12.
 */
public class PerfectClass extends BmobObject implements Serializable{
    private String classname;
    private String class_image;
    private String class_web;
    private String class_teacher;
    private BmobFile Image;
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
    @Override
    public String toString() {
        return new String(classname+"   "+class_teacher);
    }
}
