package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public  class FindBaseAdapter extends BaseRecycleAdapter {


    public FindBaseAdapter(Context conn) {
        super(conn);
    }

    public FindBaseAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.find_zuixin_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        //头像
        ImageView ivew = ((ImageView)holder.getView(R.id.find_zuixin_touxiang));
        int a = 0;
        String url = ((DongTaiBean.DataBean)data.get(position)).getFace();
        Glide.with(conn).load(Contants.BASE_URL + url).into(ivew);

        //名称和年龄
        ((TextView)holder.getView(R.id.find_zuixin_name)).setText(((DongTaiBean.DataBean)data.get(position)).getNickname()+"");
        ((TextView)holder.getView(R.id.find_zuixin_age)).setText(((DongTaiBean.DataBean)data.get(position)).getAge()+"");

        //城市
        ((TextView)holder.getView(R.id.find_zuixin_dizhi)).setText(((DongTaiBean.DataBean)data.get(position)).getCity());

        //时间
        ((TextView)holder.getView(R.id.find_zuixin_data)).setText(((DongTaiBean.DataBean)data.get(position)).getCreate_time());

        //title
        ((TextView)holder.getView(R.id.find_zuixin_title)).setText(((DongTaiBean.DataBean)data.get(position)).getContent());


        //点赞
        ((TextView)holder.getView(R.id.find_zuixin_dianzan)).setText(((DongTaiBean.DataBean)data.get(position)).getZan()+"");

        //评论
        ((TextView)holder.getView(R.id.find_zuixin_pinglun)).setText(((DongTaiBean.DataBean)data.get(position)).getPing_count()+"");

        //礼物
        ((TextView)holder.getView(R.id.find_zuixin_liwu)).setText(((DongTaiBean.DataBean)data.get(position)).getGift());

        //自己是否点赞

        //图片的处理
        if(((DongTaiBean.DataBean)data.get(position)).getPhoto_type()==2){
            //有图  给recycleview设置数据
            RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.find_zuixin_tuji);




        }
    }



}
