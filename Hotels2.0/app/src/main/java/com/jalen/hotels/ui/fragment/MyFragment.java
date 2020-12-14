package com.jalen.hotels.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseFragment;
import com.jalen.hotels.ui.AboutActivity;
import com.jalen.hotels.ui.FeedBackActivity;
import com.jalen.hotels.ui.LoginActivity;
import com.jalen.hotels.util.AppConstant;
import com.jalen.hotels.util.SPUtil;
import com.jalen.hotels.util.StringUtil;

/**
 * 我的页面
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_name;
    private SwitchCompat sc_push, sc_wifi;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        tv_name = rootView.findViewById(R.id.tv_name);
        sc_push = rootView.findViewById(R.id.sc_push);
        sc_wifi = rootView.findViewById(R.id.sc_wifi);
    }

    @Override
    public void initData() {
        String phone = SPUtil.getString(getActivity(), AppConstant.LOGIN_PHONE, null);
        if (StringUtil.isNotBlank(phone))
            tv_name.setText(phone);
        boolean wifi = SPUtil.getBoolean(getActivity(), AppConstant.SP_WIFI, false);
        boolean push = SPUtil.getBoolean(getActivity(), AppConstant.SP_PUSH, false);
        sc_push.setChecked(push);
        sc_wifi.setChecked(wifi);
    }

    @Override
    public void initEvent() {
        rootView.findViewById(R.id.tv_feedback).setOnClickListener(this);
        rootView.findViewById(R.id.tv_about).setOnClickListener(this);
        rootView.findViewById(R.id.tv_exit).setOnClickListener(this);
        sc_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SPUtil.putBoolean(getActivity(), AppConstant.SP_PUSH, b);
            }
        });
        sc_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SPUtil.putBoolean(getActivity(), AppConstant.SP_WIFI, b);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exit:
                SPUtil.putString(getActivity(), AppConstant.LOGIN_PHONE, "");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.tv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }
}
