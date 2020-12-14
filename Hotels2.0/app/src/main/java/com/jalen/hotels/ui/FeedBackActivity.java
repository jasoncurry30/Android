package com.jalen.hotels.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;
import com.jalen.hotels.bean.FeedbackBean;
import com.jalen.hotels.util.AppConstant;
import com.jalen.hotels.util.SPUtil;
import com.jalen.hotels.util.StringUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 反馈
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title, tv_right;
    private EditText et_content;

    @Override
    public void initView() {
        setContentView(R.layout.activity_feedback);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        et_content = findViewById(R.id.et_content);
    }

    @Override
    public void initData() {
        tv_title.setText("意见反馈");
        tv_right.setText("提交");
    }

    @Override
    public void initEvent() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                commit();
                break;
        }
    }


    private void commit() {
        String s = et_content.getText().toString();
        if (StringUtil.isBlank(s.trim())) {
            showMsg("提交内容不能为空");
        } else {
            FeedbackBean feedbackBean = new FeedbackBean();
            String phone = SPUtil.getString(context, AppConstant.LOGIN_PHONE, null);
            feedbackBean.content = s;
            feedbackBean.phone = phone;
            feedbackBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {//创建失败
                        showMsgs("提交失败" + e.toString());
                    } else {//创建成功
                        showMsgs("提交成功");
                        finish();
                    }
                }
            });
        }
    }
}
