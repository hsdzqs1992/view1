package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.HistoryBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_queding)
    ImageView searchQueding;
    @BindView(R.id.search_history)
    LinearLayout searchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {


        //初始化历史搜索数据
        OkGo.<String>post(Contants.history_select)
                .params("token", SpUtils.getString(SearchActivity.this,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        //// TODO: 2017/11/29  解析历史数据加入流式布局中
                        Gson gson = new Gson();
                        HistoryBean h= gson.fromJson(response.body(),HistoryBean.class);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    @OnClick({R.id.search_back, R.id.search_queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_queding:
                sousuo();
                break;
        }
    }

    //搜索
    private void sousuo() {
        String text = searchEt.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            CommentUtils.toast(SearchActivity.this,"请输入内容");
            return;
        }


        OkGo.<String>post(Contants.select)
                .params("token", SpUtils.getString(SearchActivity.this,"token",""))
                .params("page",1)
                .params("keyword",text)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }
}
