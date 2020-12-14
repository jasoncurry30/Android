package com.jalen.hotels.bean;

import cn.bmob.v3.BmobObject;

/**
 */

public class User extends BmobObject {
    public String phone;
    public String pwd;
    public String avatar;

    public User(String name, String pwd) {
        this.phone = name;
        this.pwd = pwd;
    }


    public User(String phone, String pwd, String avatar) {
        this.phone = phone;
        this.pwd = pwd;
        this.avatar = avatar;
    }
}
