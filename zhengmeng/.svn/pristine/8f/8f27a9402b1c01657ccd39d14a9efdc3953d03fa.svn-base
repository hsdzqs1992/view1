package com.zhuye.zhengmeng.bangdan.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.zhengmeng.Constant;
import com.zhuye.zhengmeng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpc on 2017/11/7.
 */

public class SongsListAdapter extends BaseQuickAdapter<SonglistBean.DataBean, BaseViewHolder> {
    private List<SonglistBean.DataBean> data_list = new ArrayList();
    private Context mContext;

    public SongsListAdapter(int layoutResId, @Nullable List<SonglistBean.DataBean> data, Context context) {
        super(layoutResId, data);
        this.data_list = data;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    @Override
    protected void convert(BaseViewHolder helper, SonglistBean.DataBean item) {
        helper.setText(R.id.name_song, item.getSong_name());
        helper.setText(R.id.song_size, item.getSong_name());
        helper.addOnClickListener(R.id.btn_yanchang);
        Glide.with(mContext)
                .load(Constant.BASE_URL2 + item.getSong_img())
                .centerCrop()
                .placeholder(R.mipmap.default_img)
                .into((ImageView) helper.getView(R.id.img_song));
    }
}
