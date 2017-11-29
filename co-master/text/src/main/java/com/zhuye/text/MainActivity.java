package com.zhuye.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();

        findViewById(R.id.nn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });

    }

    private void initData() {

        PictureSelector.create(MainActivity.this)
                .openGallery(0)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(10)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(false)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .glideOverride(300,300)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(16,9)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片 true or false
                .compressSavePath(getPath())//压缩图片保存地址
                .freeStyleCropEnabled(false)
                // 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    public String getPath() {
        return getExternalCacheDir()+"/files";
    }
}
