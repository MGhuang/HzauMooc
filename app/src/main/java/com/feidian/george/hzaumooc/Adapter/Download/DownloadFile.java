package com.feidian.george.hzaumooc.Adapter.Download;

/**
 * Created by lenovo on 2016/4/4.
 */
public class DownloadFile {
    private String name;
    private String date;
    private String size;
    private String path;
    private int image;
    public DownloadFile(String name, String date, String size, String path, int image){
        this.name = name;
        this.date = date;
        this.size = size;
        this.path = path;
        this.image = image;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setSize(String size){
        this.size = size;
    }
    public String getSize(){
        return size;
    }
    public String getPath() {return path;}
    public void setPath(String path) {this.path = path;}
    public void setImage(int image){this.image = image;}
    public int getImage(){return image;}
}
