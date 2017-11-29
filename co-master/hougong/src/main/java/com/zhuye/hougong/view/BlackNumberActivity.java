package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlackNumberActivity extends AppCompatActivity {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_zhuanqian)
    TextView mywalotZhuanqian;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle2);
        ButterKnife.bind(this);
        mywalotQianbao.setText("黑名单");

        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add("sdfasdf" + i);
        }

        blackNumberAdapter= new BlackNumberAdapter(this);

        initData();
        commotRecycle.setAdapter(blackNumberAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
    }
    BlackNumberAdapter blackNumberAdapter;
    private void initData() {
        OkGo.<String>post(Contants.blacklist)
                .params("token", SpUtils.getString(BlackNumberActivity.this,"token",""))
                .params("page",1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue() {
                        CommentUtils.toast(BlackNumberActivity.this,"sdf");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String s = response.body();
                        Gson gson = new Gson();
                        MyFriendsBean mybean = gson.fromJson(response.body(),MyFriendsBean.class);
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
