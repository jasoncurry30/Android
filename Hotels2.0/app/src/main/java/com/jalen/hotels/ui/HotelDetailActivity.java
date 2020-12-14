package com.jalen.hotels.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.adapter.CommentAdapter;
import com.jalen.hotels.adapter.HotelRoomAdapter;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.Comment;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.HotelRoom;
import com.jalen.hotels.util.ImageUtils;
import com.jalen.hotels.util.StringUtil;
import com.jalen.hotels.widget.NestingListView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Subscriber;

/**
 * 酒店详情
 */

public class HotelDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_hotel;
    private TextView tv_name, tv_address, tv_content, tv_time, tv_tag;

    private NestingListView ngv_comment, ngv_type;

    private Hotel hotel;

    @Override
    public void initView() {
        setContentView(R.layout.activity_hotel_detail);
        iv_hotel = findViewById(R.id.iv_hotel);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_content = findViewById(R.id.tv_content);
        tv_time = findViewById(R.id.tv_time);
        tv_tag = findViewById(R.id.tv_tag);
        ngv_comment = findViewById(R.id.ngv_comment);
        ngv_type = findViewById(R.id.ngv_type);
    }

    @Override
    public void initData() {
        hotel = (Hotel) getIntent().getSerializableExtra("vo");
        if (null != hotel) {
            if (StringUtil.isNotBlank(hotel.pic)) {
                ImageUtils.loadImage(context, iv_hotel, hotel.pic, false);
            }
            if (StringUtil.isNotBlank(hotel.name))
                tv_name.setText(hotel.name);
            if (StringUtil.isNotBlank(hotel.address))
                tv_address.setText(hotel.address);
            if (StringUtil.isNotBlank(hotel.content))
                tv_content.setText(hotel.content);
            if (StringUtil.isNotBlank(hotel.times))
                tv_time.setText(hotel.times);
            if (StringUtil.isNotBlank(hotel.tag))
                tv_tag.setText(hotel.tag);

            initRoomData();
            initCommentData();
        }
    }

    private void initRoomData() {
        BmobQuery<HotelRoom> bmobQuery = new BmobQuery<HotelRoom>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjectsObservable(HotelRoom.class)
                .subscribe(new Subscriber<List<HotelRoom>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<HotelRoom> persons) {
                        if (null != persons && persons.size() > 0) {
                            HotelRoomAdapter hotelRoomAdapter = new HotelRoomAdapter(persons, context);
                            ngv_type.setAdapter(hotelRoomAdapter);
                        }
                    }
                });
    }

    private void initCommentData() {
        BmobQuery<Comment> bmobQuery = new BmobQuery<Comment>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjectsObservable(Comment.class)
                .subscribe(new Subscriber<List<Comment>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<Comment> persons) {
                        if (null != persons && persons.size() > 0) {
                            CommentAdapter commentAdapter = new CommentAdapter(persons, context);
                            ngv_comment.setAdapter(commentAdapter);
                        }
                    }
                });
    }


    @Override
    public void initEvent() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_tel).setOnClickListener(this);
        findViewById(R.id.iv_safe).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_tel:
                break;
            case R.id.iv_safe:
                break;
        }
    }
}
