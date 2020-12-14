package com.jalen.hotels.bean;

import cn.bmob.v3.BmobObject;

/**
 */

public class SearchBean extends BmobObject {
    public String type;
    public String name;

    public SearchBean(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
