package com.feidian.george.hzaumooc.Adapter.Evalute;

import cn.bmob.v3.BmobObject;

/**
 * Created by lenovo on 2016/5/14.
 */
public class Evalute extends BmobObject {
    private String words;
    private String courseName;
    public Evalute(String words){
        this.words = words;
    }
    public Evalute(String words,String courseName){
        this(words);
        this.courseName = courseName;
    }
    public void setWords(String words){
        this.words = words;
    }
    public String getWords(){
        return words;
    }
    public void setVideoName(String courseName){
        this.courseName = courseName;
    }
    public String getCourseName(){
        return courseName;
    }
}
