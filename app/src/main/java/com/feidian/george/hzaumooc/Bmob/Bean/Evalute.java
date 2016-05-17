package com.feidian.george.hzaumooc.Bmob.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/5/17.
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