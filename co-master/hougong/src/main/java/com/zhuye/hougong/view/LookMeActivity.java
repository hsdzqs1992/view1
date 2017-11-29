package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.LookMeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookMeActivity extends AppCompatActivity {

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
        mywalotQianbao.setText("谁看过我");

        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add("sdfasdf" + i);
        }

        LookMeAdapter blackNumberAdapter = new LookMeAdapter(this, list);

        commotRecycle.setAdapter(blackNumberAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
    }
}
