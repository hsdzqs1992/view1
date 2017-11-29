package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okserver.upload.UploadListener;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.SpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaBuActivity extends AppCompatActivity {


    @BindView(R.id.fubu_content)
    EditText mFubuContent;
    @BindView(R.id.fabu_xuanze)
    TextView mFabuXuanze;
    @BindView(R.id.fabu_weizhi)
    TextView mFabuWeizhi;
    @BindView(R.id.fabu_fabiao)
    TextView fabuFabiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        OkGo.<String>post(Contants.cityindex)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---",response.body());
                    }
                });
    }

    @OnClick({R.id.fabu_xuanze, R.id.fabu_weizhi,R.id.fabu_fabiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabu_xuanze:
                //startActivity(new Intent(FaBuActivity.this, SelectPictureActivity.class));
                seleciPicture();
                break;
            case R.id.fabu_weizhi:




                break;
            case R.id.fabu_fabiao:



                fabiao();
                break;
        }
    }

    private void seleciPicture() {

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setSelectLimit(9);
        imagePicker.setCrop(false);
        Intent intent = new Intent(getApplicationContext(), ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }

    List<ImageItem> images = new ArrayList<>();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                //noinspection unchecked
                images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                //tasks = adapter.updateData(images);



//                Intent intent = new Intent(FaBuActivity.this,PingLunActivity.class);
//                intent.putParcelableArrayListExtra("image", (ArrayList<? extends Parcelable>) images);


            } else {
                //showToast("没有数据");
                CommentUtils.toast(FaBuActivity.this,"没有数据");
            }
        }
    }

    private void fabiao() {

        String content = mFubuContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            CommentUtils.toast(FaBuActivity.this,"请输入内容");
            return;
        }

        ArrayList<File> files = new ArrayList<>();
        if(images != null && images.size() > 0){

            for (int i = 0 ;i< images.size();i++){
                File file = new File(images.get(i).path);
                files.add(file);
            }
        }

         OkGo.<String>post(Contants.addcontent)
                .params("token", SpUtils.getString(FaBuActivity.this,"token",""))
                .params("content",content)
                .params("city_code","100")
                .params("city","北京市")
                 .params("file",files.get(0))//.addFileParams("file",files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---",response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---",response.body());
                    }
                });
//
//        UploadTask<String> task = OkUpload.request("haha", postRequest)//
//                .priority(10)//
//                .save()
//                .register(new MyListener("haa"));
//        //values.add(task);
//
//        task.start();


    }

    public class  MyListener<T> extends UploadListener{

        public MyListener(Object tag) {
            super(tag);
        }

        @Override
        public void onStart(Progress progress) {

        }

        @Override
        public void onProgress(Progress progress) {

        }

        @Override
        public void onError(Progress progress) {

            Log.d("dsa","safa");
        }

        @Override
        public void onFinish(Object o, Progress progress) {
            Log.d("dsa","safa");
        }

        @Override
        public void onRemove(Progress progress) {

        }
    }
}
