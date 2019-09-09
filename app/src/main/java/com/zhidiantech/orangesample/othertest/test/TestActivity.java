package com.zhidiantech.orangesample.othertest.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zhidiantech.orangeframe.imageloader.ImageConfigImpl;
import com.zhidiantech.orangeframe.imageloader.ImageLoader;
import com.zhidiantech.orangeframe.support.eventbus.EventBusUtils;
import com.zhidiantech.orangesample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.image_layout)
    ImageLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        imageLayout.addImage("http://lc-orbhp4de.cn-n1.lcfile.com/07f15e268b57cb0b5511.png",200,200);
        imageLayout.addImage("http://lc-orbhp4de.cn-n1.lcfile.com/07f15e268b57cb0b5511.png",300,300);
    }
}
