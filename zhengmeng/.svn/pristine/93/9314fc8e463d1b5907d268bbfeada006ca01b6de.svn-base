package com.zhuye.zhengmeng.user;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zhuye.zhengmeng.R;
import com.zhuye.zhengmeng.base.BaseActivity;
import com.zhuye.zhengmeng.view.MyAppTitle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBankCardActivity extends BaseActivity {


    @BindView(R.id.titleBar)
    MyAppTitle titleBar;

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {
        setTitle();
    }

    private void setTitle() {
        titleBar.initViewsVisible(true, true, false, false);
        titleBar.setTitleSize(20);
        titleBar.setAppTitle("我的银行卡");
        titleBar.setOnLeftButtonClickListener(new MyAppTitle.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_my_bank_card);
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

}
