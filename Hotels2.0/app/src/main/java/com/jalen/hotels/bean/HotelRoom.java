package com.jalen.hotels.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Date 2018/3/28 14:06
 * @Author Jalen
 * @Email:c9n9m@163.com
 * @Description
 */

public class HotelRoom extends BmobObject {
    public String name;
    public String typeImg;
    public String price;
    public String tag;

    public HotelRoom(String name, String typeImg, String price, String tag) {
        this.name = name;
        this.typeImg = typeImg;
        this.price = price;
        this.tag = tag;
    }
}
