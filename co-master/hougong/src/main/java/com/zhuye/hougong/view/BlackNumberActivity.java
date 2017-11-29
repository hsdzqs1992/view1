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
import com.zhuye.hougong.adapter.BlackNumberAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlackNumberActivity extends AppCompatActivity {


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

    BlackNumberAdapter blackNumberAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle2);
        ButterKnife.bind(this);
        mMywalotQianbao.setText("黑名单");
        mSongliwuTixian.setVisibility(View.INVISIBLE);


        blackNumberAdapter = new BlackNumberAdapter(this);

        initData();
        mCommotRecycle.setAdapter(blackNumberAdapter);
        mCommotRecycle.setLayoutManager(new LinearLayoutManager(this));
    }



    private void initData() {
        OkGo.<String>post(Contants.blacklist)
                .params("token", SpUtils.getString(BlackNumberActivity.this, "token", ""))
                .params("page", 1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue() {
                        CommentUtils.toast(BlackNumberActivity.this, "sdf");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String s = response.body();
                        Gson gson = new Gson();
                        MyFriendsBean mybean = gson.fromJson(response.body(), MyFriendsBean.class);
                        //CommentUtils.toast(BlackNumberActivity.this,s);
                        blackNumberAdapter.addData(mybean.getData());
                    }
                });
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }

}
