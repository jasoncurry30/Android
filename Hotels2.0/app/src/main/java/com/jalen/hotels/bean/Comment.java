package com.jalen.hotels.bean;

import cn.bmob.v3.BmobObject;

/**
 * 评论
 */

public class Comment extends BmobObject {
    public String content;
    public String time;
    public String user;

    public Comment(String content, String time, String user) {
        this.content = content;
        this.time = time;
        this.user = user;
    }
}
