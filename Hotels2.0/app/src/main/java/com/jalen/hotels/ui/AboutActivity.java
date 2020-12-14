package com.jalen.hotels.ui;

import android.view.View;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.base.BaseActivity;

/**
 * 关于
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        tv_title = findViewById(R.id.tv_title);
    }

    @Override
    public void initData() {
        tv_title.setText("关于我们");
    }

    @Override
    public void initEvent() {
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
