package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.GuanZhuAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzzy on 2017/11/22.
 */

public class FansActivity extends AppCompatActivity {


    @BindView(R.id.person_detail_back)
    ImageView mPersonDetailBack;
    @BindView(R.id.mywalot_zhuanqian)
    TextView mMywalotZhuanqian;
    @BindView(R.id.mywalot_qianbao)
    TextView mMywalotQianbao;
    @BindView(R.id.guanzhu_recycleview)
    RecyclerView mGuanzhuRecycleview;
    @BindView(R.id.guanzhu_refesh)
    MaterialRefreshLayout mGuanzhuRefesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);
        mMywalotQianbao.setText("我的粉丝");

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("sdfasdf" + i);
        }

        guanZhuAdapter = new GuanZhuAdapter(this);
        mGuanzhuRecycleview.setAdapter(guanZhuAdapter);
        mGuanzhuRecycleview.setLayoutManager(new LinearLayoutManager(this));

        guanZhuAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //Toast.makeText(GuanZhuActivity.this,"haha",Toast.LENGTH_SHORT).show();

            }
        });
        initData();

    }

    private void initData() {
        OkGo.<String>post(Contants.loveme)
                .params("token", SpUtils.getString(FansActivity.this,"token",""))
                .params("page",1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue() {

                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String i = response.body();
                        Gson gson = new Gson();
                        MyFriendsBean myfriend = gson.fromJson(response.body(),MyFriendsBean.class);
                        guanZhuAdapter.addData(myfriend.getData());

                        //Log.i("llllll",i);
                    }
                });
    }

    GuanZhuAdapter guanZhuAdapter;

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }
}
