package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.FindPagerAdapter2;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.SpUtils;
import com.zhuye.hougong.view.FaBuActivity;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class FindFragment extends BaseFragment {
   // private MyToolbar myToolbar;
    private ViewPager mviewpager;

    private PagerSlidingTabStrip mTabStrip;
    private ImageView fabu;
    FindPagerAdapter2 finAdapter;
    @Override
    protected void initView() {
       //myToolbar = rootView.findViewById(R.id.find_toolbar);

        //initToolBar();
        mviewpager = rootView.findViewById(R.id.find_viewpager);

        mTabStrip=rootView.findViewById(R.id.tab_strip_find);
        fabu=rootView.findViewById(R.id.message);
        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h8));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);


        finAdapter = new FindPagerAdapter2(getActivity());
        mviewpager.setAdapter(finAdapter);
        mTabStrip.setViewPager(mviewpager);




    }

    @Override
    protected void initData() {
        super.initData();
        //初始化关注数据
        initFindData(1,"201");


        //初始化同城数据



   }

    private void initFindData(int type ,String city_code) {
        OkGo.<String>post(Contants.new_dynamic)
                .params("token", SpUtils.getString(getActivity(),"token",""))
                .params("page",1)
                .params("type",city_code)
                .params("city_code","100")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        Gson gson = new Gson();
                        DongTaiBean dongTaiBean = gson.fromJson(response.body(),DongTaiBean.class);
                        if(finAdapter!=null){
                            finAdapter.setFindGuanZhuData(dongTaiBean);
                            finAdapter.setFindGuanZhuData(dongTaiBean);
                            finAdapter.setFindZhuiXinData(dongTaiBean);
                           // finAdapter.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FaBuActivity.class));
            }
        });
    }

    private void initToolBar() {

       // myToolbar.homeLeftIcon.setText(R.string.huangguan);
        //myToolbar.homeLeftIcon.setTypeface(((MainActivity)getActivity()).typeface);
        //myToolbar.homeRightIcon.setText(R.string.loudou);
       // myToolbar.homeRightIcon.setTypeface(((MainActivity)getActivity()).typeface);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_find;
    }
}
