package com.jalen.hotels.ui.fragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.jalen.hotels.R;
import com.jalen.hotels.adapter.BannerAdapter;
import com.jalen.hotels.adapter.HotelAdapter;
import com.jalen.hotels.adapter.SearchAdapter;
import com.jalen.hotels.base.BaseFragment;
import com.jalen.hotels.bean.BannerBean;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.SearchBean;
import com.jalen.hotels.ui.HotelDetailActivity;
import com.jalen.hotels.util.QueryHotelCallBack;
import com.jalen.hotels.util.ScreenUtils;
import com.jalen.hotels.util.ServerUtils;
import com.jalen.hotels.util.StringUtil;
import com.jalen.hotels.widget.NestingGridView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Subscriber;

/**
 * 搜索页面
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {

    private NestingGridView ngv_hot, ngv_business, ngv_trafic, ngv_landscape;
    private SearchAdapter adapterHot, adapterBusiness, adapterTrafic, adapterLandscape;
    private List<SearchBean> listHot, listBusiness, listTrafic, listLandscape;

    private ListView lv;
    private List<Hotel> list;
    private ScrollView scrollView;
    private EditText et_search;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        lv = rootView.findViewById(R.id.lv);
        scrollView = rootView.findViewById(R.id.scrollView);
        et_search = rootView.findViewById(R.id.et_search);
        ngv_hot = rootView.findViewById(R.id.ngv_hot);
        ngv_business = rootView.findViewById(R.id.ngv_business);
        ngv_trafic = rootView.findViewById(R.id.ngv_trafic);
        ngv_landscape = rootView.findViewById(R.id.ngv_landscape);
    }

    @Override
    public void initData() {
        initSearchData();
    }

    @Override
    public void initEvent() {
        rootView.findViewById(R.id.tv_search).setOnClickListener(this);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (StringUtil.isEmpty(s)) {
                    clear();
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), HotelDetailActivity.class);
                Hotel hotel = list.get(i);
                intent.putExtra("vo", hotel);
                startActivity(intent);
            }
        });
    }

    private void initSearchData() {
        BmobQuery<SearchBean> bmobQuery = new BmobQuery<SearchBean>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjectsObservable(SearchBean.class)
                .subscribe(new Subscriber<List<SearchBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ServerUtils", e.toString());
                    }

                    @Override
                    public void onNext(List<SearchBean> persons) {
                        setSearchDatas(persons);
                    }
                });
    }

    private void setSearchDatas(List<SearchBean> list) {
        try {
            listHot = new ArrayList<>();
            listBusiness = new ArrayList<>();
            listTrafic = new ArrayList<>();
            listLandscape = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                SearchBean searchBean = list.get(i);
                switch (searchBean.type) {
                    case "热门搜索":
                        listHot.add(searchBean);
                        break;
                    case "高校":
                        listBusiness.add(searchBean);
                        break;
                    case "地铁站":
                        listTrafic.add(searchBean);
                        break;
                    case "景点":
                        listLandscape.add(searchBean);
                        break;
                }
            }

            adapterHot = new SearchAdapter(listHot, getActivity());
            adapterBusiness = new SearchAdapter(listBusiness, getActivity());
            adapterTrafic = new SearchAdapter(listTrafic, getActivity());
            adapterLandscape = new SearchAdapter(listLandscape, getActivity());

            ngv_hot.setAdapter(adapterHot);
            ngv_business.setAdapter(adapterBusiness);
            ngv_trafic.setAdapter(adapterTrafic);
            ngv_landscape.setAdapter(adapterLandscape);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                String key = et_search.getText().toString().trim();
                if (StringUtil.isBlank(key)) {
                    showMsg("请输入需要查询的关键字");
                    return;
                }
                ScreenUtils.hideKeyboard(et_search);
                search(key);
                break;
        }
    }

    private void search(String keyword) {
        ServerUtils.queryHotelInfo(keyword, new QueryHotelCallBack() {
            @Override
            public void success(List<Hotel> movements) {
                if (null != movements && movements.size() > 0) {
                    list = movements;
                    HotelAdapter hotelAdapter = new HotelAdapter(list, getActivity());
                    lv.setAdapter(hotelAdapter);
                    lv.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                } else {
                    showMsg("暂无查询结果");
                }
            }

            @Override
            public void fail(String e) {
                showMsg(e);
            }
        });
    }

    private void clear() {
        scrollView.setVisibility(View.VISIBLE);
        lv.setVisibility(View.GONE);
    }
}
