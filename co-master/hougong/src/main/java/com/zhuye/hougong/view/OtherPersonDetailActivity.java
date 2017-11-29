package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherPersonDetailActivity extends AppCompatActivity {

    @BindView(R.id.other_person_back)
    ImageView mOtherPersonBack;
    @BindView(R.id.other_person_detail_touxiang)
    ImageView mOtherPersonDetailTouxiang;
    @BindView(R.id.other_person_detail_name)
    TextView mOtherPersonDetailName;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.tv_friends)
    TextView mTvFriends;
    @BindView(R.id.other_person_detail_xiangce)
    TextView mOtherPersonDetailXiangce;
    @BindView(R.id.other_person_detail_xiangcego)
    TextView mOtherPersonDetailXiangcego;
    @BindView(R.id.other_person_detail_recycleview)
    RecyclerView mOtherPersonDetailRecycleview;
    @BindView(R.id.other_person_detail_ziliao)
    TextView mOtherPersonDetailZiliao;
    @BindView(R.id.other_person_detail_age)
    TextView mOtherPersonDetailAge;
    @BindView(R.id.other_person_detail_xingzuo)
    TextView mOtherPersonDetailXingzuo;
    @BindView(R.id.other_person_detail_sex)
    TextView mOtherPersonDetailSex;
    @BindView(R.id.other_person_detail_city)
    TextView mOtherPersonDetailCity;
    @BindView(R.id.other_person_detail_shifouguan)
    Button mOtherPersonDetailShifouguan;
    @BindView(R.id.person_jiahei)
    TextView mPersonJiahei;
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_detail);
        ButterKnife.bind(this);
       p= getIntent().getStringExtra("personinfo");
    }

    @OnClick({R.id.other_person_back,R.id.person_jiahei, R.id.other_person_detail_touxiang, R.id.other_person_detail_name, R.id.tv_follow, R.id.tv_fans, R.id.tv_friends, R.id.other_person_detail_xiangce, R.id.other_person_detail_xiangcego, R.id.other_person_detail_recycleview, R.id.other_person_detail_ziliao, R.id.other_person_detail_age, R.id.other_person_detail_xingzuo, R.id.other_person_detail_sex, R.id.other_person_detail_city, R.id.other_person_detail_shifouguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.other_person_back:
                break;
            case R.id.other_person_detail_touxiang:
                break;
            case R.id.other_person_detail_name:
                break;
            case R.id.tv_follow:
                break;
            case R.id.tv_fans:
                break;
            case R.id.tv_friends:
                break;
            case R.id.other_person_detail_xiangce:
                break;
            case R.id.other_person_detail_xiangcego:
                break;
            case R.id.other_person_detail_recycleview:
                break;
            case R.id.other_person_detail_ziliao:
                break;
            case R.id.other_person_detail_age:
                break;
            case R.id.other_person_detail_xingzuo:
                break;
            case R.id.other_person_detail_sex:
                break;
            case R.id.other_person_detail_city:
                break;
            case R.id.other_person_detail_shifouguan:
                break;
            case R.id.person_jiahei:
                
                jiaheimingdan();
                break;
        }
    }

    private void jiaheimingdan() {

        OkGo.<String>post(Contants.black)
                .params("uid",p)
                .params("token", SpUtils.getString(OtherPersonDetailActivity.this,"token",""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue() {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");
                    }
                });

    }
}
