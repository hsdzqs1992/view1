package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.MyFriendsAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriendsActivity extends AppCompatActivity {


    @BindView(R.id.person_detail_back)
    ImageView mPersonDetailBack;
    @BindView(R.id.songliwu_tixian)
    TextView mSongliwuTixian;
    @BindView(R.id.mywalot_qianbao)
    TextView mMywalotQianbao;
    @BindView(R.id.commot_recycle)
    RecyclerView mCommotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout mCommonMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle2);
        ButterKnife.bind(this);
        mMywalotQianbao.setText("我的好友");

        guanZhuAdapter= new MyFriendsAdapter(this);
        mCommotRecycle.setAdapter(guanZhuAdapter);
        mCommotRecycle.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {
        OkGo.<String>post(Contants.mylove)
                .params("token", SpUtils.getString(MyFriendsActivity.this,"token",""))
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

                        Log.i("llllll",i);
                    }
                });
    }

    MyFriendsAdapter guanZhuAdapter;
    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }
}
