package com.jalen.hotels.util;

import android.text.TextUtils;
import android.util.Log;

import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Subscriber;

/**
 * bmob后台服务端工具类
 */
public class ServerUtils {


    //校验学生
    public static void queryStudentInfo(String phone, String pwd, final QueryUserCallBack queryStudentCallBack) {
        final BmobQuery<User> bmobQuery = new BmobQuery<User>();
        if (!TextUtils.isEmpty(pwd))
            bmobQuery.addWhereEqualTo("pwd", pwd);
        if (!TextUtils.isEmpty(phone))
            bmobQuery.addWhereEqualTo("phone", phone);
        bmobQuery.setLimit(1000);
        bmobQuery.order("createdAt");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//		observable形式
        bmobQuery.findObjectsObservable(User.class)
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        queryStudentCallBack.fail(e.toString());
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<User> persons) {
                        queryStudentCallBack.success(persons);
                    }
                });
    }

    public static void queryHotelInfo(String keyword, final QueryHotelCallBack queryHotelCallBack) {
        final BmobQuery<Hotel> bmobQuery = new BmobQuery<Hotel>();
        if (!TextUtils.isEmpty(keyword))
            bmobQuery.addWhereEqualTo("name", keyword);
//            bmobQuery.addWhereEqualTo("name", keyword);
        bmobQuery.setLimit(1000);
        bmobQuery.order("createdAt");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//		observable形式
        bmobQuery.findObjectsObservable(Hotel.class)
                .subscribe(new Subscriber<List<Hotel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        queryHotelCallBack.fail(e.toString());
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<Hotel> persons) {
                        queryHotelCallBack.success(persons);
                    }
                });
    }


}
