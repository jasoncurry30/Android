package com.jalen.hotels.bean;

import cn.bmob.v3.BmobObject;


public class BannerBean extends BmobObject {
    public String imageUrl;

    public String actionUrl;

    public String title;

    public BannerBean(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
