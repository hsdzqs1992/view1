package com.zhuye.hougong.weidgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zzzy on 2017/11/28.
 */

public class MyRecycleView extends RecyclerView {
    public MyRecycleView(Context context) {
        this(context,null);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {

        Float startX =0f ;
        Float startY =0f;

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = e.getX();
                startY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Float moveX = e.getX();
                Float moveY = e.getX();

                if (Math.abs(moveX-startX)<Math.abs(moveY-startY)){
                    return false;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(e);
    }
}
