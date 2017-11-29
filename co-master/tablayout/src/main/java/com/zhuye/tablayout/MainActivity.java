package com.zhuye.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyRecycleView mMyrecycle;
    List data= new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyrecycle = (MyRecycleView) findViewById(R.id.rv);



        MyAdapter1 myAdapter = new MyAdapter1();
        mMyrecycle.setAdapter(myAdapter);

        for (int i =0 ;i <10 ;i++){
            data.add("hasda"+i);
        }


        mMyrecycle.setLayoutManager(new MyGrid(this,2));

    }

    class   MyGrid  extends GridLayoutManager{

        public MyGrid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public MyGrid(Context context, int spanCount) {
            super(context, spanCount);
        }

        public MyGrid(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }


    class MyAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view1 = View.inflate(MainActivity.this,R.layout.it,null);
            MyAdapter1.MyHolder1 myHolder1 = new MyAdapter1.MyHolder1(view1);
            return myHolder1;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }



        class MyHolder1 extends RecyclerView.ViewHolder{

            public MyHolder1(View itemView) {
                super(itemView);
            }
        }


    }





    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 1){
                View view = View.inflate(MainActivity.this,R.layout.iv,null);
                MyHolder myHolder = new MyHolder(view);
                return myHolder;
            }else if(viewType==0){
                View view1 = View.inflate(MainActivity.this,R.layout.it,null);
                MyHolder1 myHolder1 = new MyHolder1(view1);
                return myHolder1;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0){
                return 1;
            }else {

                return super.getItemViewType(position);
            }
        }


        @Override
        public int getItemCount() {
            return data.size() + 1;
        }


        class MyHolder extends RecyclerView.ViewHolder{

            public MyHolder(View itemView) {
                super(itemView);
            }
        }

        class MyHolder1 extends RecyclerView.ViewHolder{

            public MyHolder1(View itemView) {
                super(itemView);
            }
        }
    }
}
