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
import com.zhuye.hougong.adapter.me.LookMeAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookMeActivity extends AppCompatActivity {


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


        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add("sdfasdf" + i);
        }

        blackNumberAdapter = new LookMeAdapter(this);

        mCommotRecycle.setAdapter(blackNumberAdapter);
        mCommotRecycle.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    LookMeAdapter blackNumberAdapter;
    private void initData() {
        OkGo.<String>post(Contants.wholookme)
                .params("token", SpUtils.getString(LookMeActivity.this,"token",""))
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
                      blackNumberAdapter.addData(myfriend.getData());

                        Log.i("llllll",i);
                    }
                });
    }


    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
    }
}
