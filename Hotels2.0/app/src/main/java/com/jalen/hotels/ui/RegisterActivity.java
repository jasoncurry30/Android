package com.jalen.hotels.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.User;
import com.jalen.hotels.util.AppConstant;
import com.jalen.hotels.util.QueryUserCallBack;
import com.jalen.hotels.util.SPUtil;
import com.jalen.hotels.util.ServerUtils;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    //注册用户名 密码 手机号 学校  课程
    private EditText et_pwd, et_tel;
    private TextView tv_title;
    private User student;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("注册");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                register();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //Register
    public void register() {
        try {
            String pwd = et_pwd.getText().toString().trim();
            final String tel = et_tel.getText().toString().trim();


            if (TextUtils.isEmpty(tel)) {
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


            student = new User(tel, pwd);
            //需要新增学生用户数据

            //1:先查询是否有该手机号的注册信息
            ServerUtils.queryStudentInfo(tel, pwd, new QueryUserCallBack() {
                @Override
                public void success(List<User> persons) {
                    if (null != persons && persons.size() > 0) {//存在记录
                        showMsgs("该手机号已注册用户");
                    } else {//不存在记录
                        saveStudent(student, tel);
                    }
                }

                @Override
                public void fail(String e) {
                    showMsgs("注册失败稍后再试" + e);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //新增学生
    private void saveStudent(User student, final String telphone) {
        student.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {//创建失败
                    showMsgs("注册失败" + e.toString());
                } else {//创建成功
                    showMsgs("注册成功");
                    //将登陆用户手机号保存到sharepreference中
                    SPUtil.putString(context, AppConstant.LOGIN_PHONE, telphone);
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                }
            }
        });
    }


}
