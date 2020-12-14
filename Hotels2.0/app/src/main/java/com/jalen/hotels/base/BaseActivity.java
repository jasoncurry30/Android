package com.jalen.hotels.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jalen.hotels.R;

/**
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
        initData();
        initEvent();
    }


    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

    protected Fragment curFragment;

    public void switchContent(int id, Fragment from, Fragment to, String tag) {
        if (from != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            /*if(null!=from){
                transaction.setCustomAnimations(
                        R.anim.in_from_right, R.anim.out_to_left,R.anim.in_from_left,R.anim.out_to_right);
            }*/
            if (!to.isAdded()) {    // 先判断是否被add过
                if (null == from) {
                    transaction.add(id, to, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
//                    transaction.setCustomAnimations(R.anim.fade_in_fragment, R.anim.fade_out_fragment).add(id, to, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(from).add(id, to, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                if (null == from) {
                    transaction.show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                } else {
                    transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                }
            }
            curFragment = to;
        }
    }

    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showMsgs(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMsg(msg);
            }
        });
    }

    public void log(String msg) {
        Log.d("TEST", msg);
    }
}
