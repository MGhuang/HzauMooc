package com.feidian.george.hzaumooc.Adapter.Person;

/**
 * Created by lenovo on 2016/4/21.
 */
public class More {
    private String name;
    private int image;
    public More(String name, int image){
        this.image = image;
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setImage(int image){
        this.image = image;
    }
    public int getImage(){
        return image;
    }
}
