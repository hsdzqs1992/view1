package com.zhuye.hougong.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.zhuye.hougong.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPictureActivity extends TakePhotoActivity {

    @BindView(R.id.select_photo)
    ImageView mSelectPhoto;
    @BindView(R.id.select_touxiang)
    ImageView mSelectTouxiang;
    @BindView(R.id.select_qite)
    ImageView mSelectQite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        ButterKnife.bind(this);
        initListener();

    }

    private void initListener() {
        mSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //selectPhoto(view);
            }
        });
    }




    private void selectPhoto(View view) {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(getTakePhoto());
        configTakePhotoOption(getTakePhoto());
        getTakePhoto().onPickMultiple(5);

    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {


        super.takeCancel();
    }

    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }
    private void configCompress(TakePhoto takePhoto){

            takePhoto.onEnableCompress(null,false);
            return ;


    }

}
