package com.jalen.hotels.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.jalen.hotels.R;
import com.jalen.hotels.adapter.BannerAdapter;
import com.jalen.hotels.adapter.HotelAdapter;
import com.jalen.hotels.base.BaseFragment;
import com.jalen.hotels.bean.BannerBean;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.ui.HotelDetailActivity;
import com.jalen.hotels.widget.NestingListView;
import com.jalen.hotels.widget.ViewPagerIndicator;
import com.jalen.hotels.widget.banner.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Subscriber;

/**
 * 主tab页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private UltraViewPager bannerLayout;
    private ViewPagerIndicator indicator;
    private NestingListView lv;


    private BannerAdapter bannerAdapter;
    private List<BannerBean> bannerBeanList;

    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        lv = rootView.findViewById(R.id.lv);
        bannerLayout = rootView.findViewById(R.id.banner);
        indicator = rootView.findViewById(R.id.indicator_circle_line);
    }

    @Override
    public void initData() {
        initBanner();
        initHotelList();
    }


    @Override
    public void initEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), HotelDetailActivity.class);
                Hotel hotel = hotelList.get(i);
                intent.putExtra("vo", hotel);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }


    private void initBanner() {
        BmobQuery<BannerBean> bmobQuery = new BmobQuery<BannerBean>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjectsObservable(BannerBean.class)
                .subscribe(new Subscriber<List<BannerBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<BannerBean> persons) {
                        try {
                            bannerBeanList = persons;
                            bannerAdapter = new BannerAdapter(getActivity(), bannerBeanList);
                            bannerLayout.setAdapter(bannerAdapter);
                            indicator.setViewPager(bannerLayout.getViewPager(), bannerBeanList.size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void initHotelList() {
        BmobQuery<Hotel> bmobQuery = new BmobQuery<Hotel>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjectsObservable(Hotel.class)
                .subscribe(new Subscriber<List<Hotel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<Hotel> lists) {
                        hotelList = lists;
                        hotelAdapter = new HotelAdapter(hotelList, getActivity());
                        lv.setAdapter(hotelAdapter);
                    }
                });


    }
}
