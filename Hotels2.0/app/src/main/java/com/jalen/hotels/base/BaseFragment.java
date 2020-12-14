package com.jalen.hotels.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = null;
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            throw new IllegalArgumentException(new Exception("尚未设置页面布局ID."));
        }
        return rootView;
    }


    public void showMsg(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showMsgs(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMsg(msg);
            }
        });
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();
}
