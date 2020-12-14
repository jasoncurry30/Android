package com.jalen.hotels.util;

import com.jalen.hotels.bean.User;

import java.util.List;

/**
 * 查询用户回调接口
 */
public interface QueryUserCallBack {
    void success(List<User> persons);

    void fail(String e);
}
