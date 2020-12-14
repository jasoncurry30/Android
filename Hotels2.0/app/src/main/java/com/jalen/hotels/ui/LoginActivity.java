package com.jalen.hotels.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.User;
import com.jalen.hotels.util.AppConstant;
import com.jalen.hotels.util.QueryUserCallBack;
import com.jalen.hotels.util.SPUtil;
import com.jalen.hotels.util.ServerUtils;

import java.util.List;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //登录手机号  密码
    private EditText et_phone, et_pwd;


    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }


    //登录用户
    public void login() {
        try {
            final String phone = et_phone.getText().toString().trim();
            final String pwd = et_pwd.getText().toString().trim();

            if (TextUtils.isEmpty(phone)) {
                showMsg("手机号不能为空");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                showMsg("密码不能为空");
                return;
            }

            if (pwd.length() < 6) {
                showMsg("请输入至少6位以上的密码");
                return;
            }

            //从后台查询数据
            ServerUtils.queryStudentInfo(phone, pwd, new QueryUserCallBack() {
                @Override
                public void success(List<User> persons) {
                    if (null != persons && persons.size() > 0) {//存在记录
                        User student = persons.get(0);
                        String studentPassword = student.pwd;
                        String studentTelphone = student.phone;

                        if (studentPassword.equals(pwd) && studentTelphone.equals(phone)) {
                            showMsgs("登录成功");
                            SPUtil.putString(context, AppConstant.LOGIN_PHONE, phone);
                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        } else {
                            showMsg("账号密码不正确");
                        }


                    } else {//不存在记录
                        showMsgs("无此手机号注册信息");
                    }
                }

                @Override
                public void fail(String e) {
                    showMsgs("登录失败稍后再试" + e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
