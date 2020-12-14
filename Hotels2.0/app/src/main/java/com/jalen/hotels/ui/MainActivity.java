package com.jalen.hotels.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.BannerBean;
import com.jalen.hotels.bean.Comment;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.HotelRoom;
import com.jalen.hotels.bean.SearchBean;
import com.jalen.hotels.bean.User;
import com.jalen.hotels.ui.fragment.HomeFragment;
import com.jalen.hotels.ui.fragment.MyFragment;
import com.jalen.hotels.ui.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {


    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private MyFragment myFragment;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        toHomeFragment();
    }

    @Override
    public void initEvent() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        toHomeFragment();
                        return true;
                    case R.id.navigation_search:
                        toSearchFragment();
                        return true;
                    case R.id.navigation_my:
                        toMyFragment();
                        return true;
                }
                return false;
            }
        });
    }

    protected void toHomeFragment() {
        String tag = HomeFragment.class.getName();
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(tag);
        if (null == homeFragment) {
            homeFragment = new HomeFragment();
        }
        switchContent(R.id.containner, curFragment, homeFragment, tag);
    }

    protected void toSearchFragment() {
        String tag = SearchFragment.class.getName();
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(tag);
        if (null == searchFragment) {
            searchFragment = new SearchFragment();
        }
        switchContent(R.id.containner, curFragment, searchFragment, tag);
    }

    protected void toMyFragment() {
        String tag = MyFragment.class.getName();
        myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag(tag);
        if (null == myFragment) {
            myFragment = new MyFragment();
        }
        switchContent(R.id.containner, curFragment, myFragment, tag);
    }
}
