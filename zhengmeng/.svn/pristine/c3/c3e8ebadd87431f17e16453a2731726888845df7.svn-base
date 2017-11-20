package com.zhuye.zhengmeng.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.zhengmeng.Constant;
import com.zhuye.zhengmeng.R;
import com.zhuye.zhengmeng.base.BaseActivity;
import com.zhuye.zhengmeng.dynamic.adapter.CommentListAdapter;
import com.zhuye.zhengmeng.dynamic.bean.CommentListBean;
import com.zhuye.zhengmeng.http.DreamApi;
import com.zhuye.zhengmeng.http.MyCallBack;
import com.zhuye.zhengmeng.widget.RoundedCornerImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

public class DynamicDetailActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {


    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.avatar)
    RoundedCornerImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.tv_fans_number)
    TextView tvFansNumber;
    @BindView(R.id.follow_state)
    TextView followState;
    @BindView(R.id.sign_name)
    TextView signName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.rb_gift)
    RadioButton rbGift;
    @BindView(R.id.rb_comment)
    RadioButton rbComment;
    @BindView(R.id.rb_zhuanfa)
    RadioButton rbZhuanfa;
    @BindView(R.id.rb_perform)
    RadioButton rbPerform;
    @BindView(R.id.hometab_radio)
    RadioGroup hometabRadio;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private String production_id;
    private CommentListAdapter commentListAdapter;
    private String token;

    int page = 1;

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {

        production_id = getIntent().getStringExtra("production_id");
        String production_path = getIntent().getStringExtra("production_path");
        String production_name = getIntent().getStringExtra("production_name");

        token = SPUtils.getInstance("userInfo").getString("token");

        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        DreamApi.getDynamicDetail(0x002, token, production_id, myCallBack);
        videoplayer.setUp(Constant.BASE_URL2 + production_path
                , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, production_name);
        videoplayer.startVideo();
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_dynamic_detail);
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    private MyCallBack myCallBack = new MyCallBack() {
        @Override
        public void onSuccess(int what, Response<String> result) {
            switch (what) {
                case 0x001:
                    try {
                        JSONObject jsonObject = new JSONObject(result.body());
                        int code = jsonObject.getInt("code");
                        if (code == 200) {
                            CommentListBean commentListBean = new Gson().fromJson(result.body(), CommentListBean.class);
                            List<CommentListBean.Data> datas;
                            datas = commentListBean.data;
                            initUi(datas);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0x002:
                    try {
                        JSONObject jsonObject = new JSONObject(result.body());
                        int code = jsonObject.getInt("code");
                        if (code == 200) {
                            String isatt = jsonObject.getString("isatt");
                            String user_id = jsonObject.getString("user_id");
                            String user_nicename = jsonObject.getString("user_nicename");
                            String avatar = jsonObject.getString("avatar");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0x003:
                    try {
                        JSONObject jsonObject = new JSONObject(result.body());
                        int code = jsonObject.getInt("code");
                        if (code == 200) {
                            refreshLayout.autoRefresh();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        @Override
        public void onFail(int what, Response<String> result) {

        }

        @Override
        public void onFinish(int what) {
            switch (what) {
                case 0x001:
                    refreshLayout.finishLoadmore();//解决加载更多一直显示
                    refreshLayout.finishRefresh();
                    break;
            }

        }
    };

    private void initUi(final List<CommentListBean.Data> datas) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentListAdapter = new CommentListAdapter(R.layout.item_comment, datas, this);
        //给RecyclerView设置适配器
        recyclerView.setAdapter(commentListAdapter);
        if (datas.size() == 0) {
            commentListAdapter.setEmptyView(R.layout.empty, recyclerView);
        }
        commentListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        commentListAdapter.isFirstOnly(false);
        commentListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_zan:
                        DreamApi.dianZan(0x003, token, commentListAdapter.getItem(position).comment_id, myCallBack);
                        break;
                    case R.id.reply_count:
                        String sen_user_id = commentListAdapter.getItem(position).sen_user_id;
                        Intent intent = new Intent(DynamicDetailActivity.this, ErjiCommentActivity.class);
                        intent.putExtra("sen_user_id", sen_user_id);
                        intent.putExtra("production_id", production_id);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        DreamApi.getCommentList(0x001, token, page, production_id, myCallBack);
    }

    @Override
    public void onLoadmore(final RefreshLayout refreshlayout) {
        page++;
        DreamApi.getCommentList(0x001, token, page, production_id, new MyCallBack() {
            @Override
            public void onSuccess(int what, Response<String> result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.body());
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        CommentListBean commentListBean = new Gson().fromJson(result.body(), CommentListBean.class);
                        List<CommentListBean.Data> datas;
                        datas = commentListBean.data;
                        if (datas.size() == 0) {
                            refreshLayout.setLoadmoreFinished(true);
                        } else {
                            commentListAdapter.addData(datas);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int what, Response<String> result) {

            }

            @Override
            public void onFinish(int what) {
                refreshlayout.finishLoadmore();//解决加载更多一直显示
                refreshlayout.finishRefresh();
            }
        });
    }
}
