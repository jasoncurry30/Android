package com.jalen.hotels.ui;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.BannerBean;
import com.jalen.hotels.bean.Comment;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.HotelRoom;
import com.jalen.hotels.bean.SearchBean;
import com.jalen.hotels.bean.User;
import com.jalen.hotels.util.AppConstant;
import com.jalen.hotels.util.SPUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;


/**
 * 欢迎页面
 */
public class SplashActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //simulateData();//首次进入需要模拟数据  放开这一行  注释下面一行
        gotoMain();//非首次进入  直接注释上面一行 放开这一行
    }

    @Override
    public void initEvent() {

    }

    /**
     * 非首次进入APP
     */
    private void gotoMain() {
        final String phone = SPUtil.getString(context, AppConstant.LOGIN_PHONE, null);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(phone)) {//用户已经登录
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {//未登录状态
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        };
        new Handler().postDelayed(runnable, 2000);
    }

    /**
     * 首次模拟数据
     */
    private void simulateData() {
        //1:模拟用户列表
        simulateUser();
        //2:模拟banner列表
        simulateBanner();
        //3:模拟搜索类型数据
        simulateSearchType();
        //5:模拟酒店房间
        simulateHotelRoom();
        //6:模拟评论数据
        simulateComment();
        //4:模拟酒店列表
        simulateHotel();
    }

    /**
     * 模拟用户数据
     */
    private void simulateUser() {
        String[] strings = getResources().getStringArray(R.array.user_names);
        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < strings.length; i++) {
            User vo = new User(strings[i], "123456");
            list.add(vo);
        }
        addBatchList(list);
    }

    /**
     * 模拟顶部banner数据
     */
    private void simulateBanner() {
        String[] strings = getResources().getStringArray(R.array.banners_imgs);
        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < strings.length; i++) {
            BannerBean vo = new BannerBean(strings[i]);
            list.add(vo);
        }
        addBatchList(list);
    }


    /**
     * 模拟搜索数据类型
     */
    private void simulateSearchType() {
        String[] types = getResources().getStringArray(R.array.search_types);
        String[] hots = getResources().getStringArray(R.array.search_hot);
        String[] businesses = getResources().getStringArray(R.array.search_business);
        String[] trafics = getResources().getStringArray(R.array.search_trafic);
        String[] landscapes = getResources().getStringArray(R.array.search_landscape);


        List<BmobObject> listHot = new ArrayList<>();
        List<BmobObject> listBusiness = new ArrayList<>();
        List<BmobObject> listTrafic = new ArrayList<>();
        List<BmobObject> listLandscape = new ArrayList<>();


        for (int i = 0; i < hots.length; i++) {
            listHot.add(new SearchBean(types[0], hots[i]));
            listBusiness.add(new SearchBean(types[1], businesses[i]));
            listLandscape.add(new SearchBean(types[3], landscapes[i]));
        }

        for (int i = 0; i < trafics.length; i++) {
            listTrafic.add(new SearchBean(types[2], trafics[i]));
        }

        addBatchList(listHot);
        addBatchList(listBusiness);
        addBatchList(listLandscape);
        addBatchList(listTrafic);
    }

    /**
     * 模拟房间类型
     */
    private void simulateHotelRoom() {
        String[] imgs = getResources().getStringArray(R.array.room_imgs);
        String[] prices = getResources().getStringArray(R.array.room_price);
        String[] tags = getResources().getStringArray(R.array.room_tag);
        String[] types = getResources().getStringArray(R.array.room_type);
        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < imgs.length; i++) {
            list.add(new HotelRoom(types[i], imgs[i], prices[i], tags[i]));
        }
        addBatchList(list);
    }


    /**
     * 模拟评论数据
     */
    private void simulateComment() {
        String[] names = getResources().getStringArray(R.array.user_names);
        String[] contents = getResources().getStringArray(R.array.comment_contents);
        String[] times = getResources().getStringArray(R.array.comment_time);
        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < names.length; i++) {
            list.add(new Comment(contents[i], times[i], names[i]));
        }
        addBatchList(list);
    }

    /**
     * 模拟酒店数据
     */
    private void simulateHotel() {
        String[] pics = getResources().getStringArray(R.array.hotels_imgs);
        String[] names = getResources().getStringArray(R.array.hotel_name);
        String[] prices = getResources().getStringArray(R.array.hotel_price);
        String[] tags = getResources().getStringArray(R.array.hotel_tag);
        String[] contents = getResources().getStringArray(R.array.hotel_content);
        String[] times = getResources().getStringArray(R.array.hotel_times);
        String[] address = getResources().getStringArray(R.array.hotel_address);

        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < pics.length; i++) {
            Hotel hotel = new Hotel();
            hotel.pic = pics[i];
            hotel.name = names[i];
            hotel.price = prices[i];
            hotel.tag = tags[i];
            hotel.content = contents[i];
            hotel.times = times[i];
            hotel.address=address[i];
            list.add(hotel);
        }
        addBatchList(list);
    }


    private void addBatchList(List<BmobObject> list) {
        new BmobBatch().insertBatch(list).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < o.size(); i++) {
                        BatchResult result = o.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {
                            log("第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                        } else {
                            log("第" + i + "个数据批量添加失败：" + ex.getMessage() + "," + ex.getErrorCode());
                        }
                    }
                } else {
                    log(e.getMessage());
                }
            }
        });
    }
}

