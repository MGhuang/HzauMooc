package com.feidian.george.hzaumooc.Adapter.Person;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by lenovo on 2016/5/18.
 */
public class Admin extends BmobObject {
    private BmobFile icon;
    private String admin;
    private String password;
    public Admin(BmobFile icon, String admin, String password){
        this.icon = icon;
        this.admin = admin;
        this.password = password;
    }
    public void setIcon(BmobFile file){
        this.icon = icon;
    }
    public BmobFile getIcon(){
        return icon;
    }
    public void setAdmin(String admin){
        this.admin = admin;
    }
    public String getAdmin(){
        return admin;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
