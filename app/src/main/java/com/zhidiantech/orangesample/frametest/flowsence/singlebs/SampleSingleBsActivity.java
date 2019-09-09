package com.zhidiantech.orangesample.frametest.flowsence.singlebs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;
import com.zhidiantech.orangeframe.support.widget.TitleBar;
import com.zhidiantech.orangeframe.util.ActivityManager;
import com.zhidiantech.orangesample.frametest.MainActivity;
import com.zhidiantech.orangesample.R;

import butterknife.BindView;


/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2018/12/29 下午4:54
 * Changes (from 2018/12/29)
 * 单业务Activity
 * 一个Presenter可以处理完的情况
 * -----------------------------------------------------------------
 */
public class SampleSingleBsActivity extends OrangeFrameActivity<SampleSingleBsPresenterImpl> implements SampleSingleBsContract.IView {
    @BindView(R.id.bt_back_home)
    Button mBackHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getSampleData();
        mBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().popAllActivityUntillOne(MainActivity.class);
                finish();
            }
        });
    }

    @Override
    protected boolean isNeedTitleBar() {
        return true;
    }
    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setTitleBarBackground(getResources().getColor(R.color.colorPrimaryDark))
        .setTitle("单业务页面");
    }

    @Override
    protected boolean isBindButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single;
    }

    @Override
    protected SampleSingleBsPresenterImpl initPresenter() {
        return new SampleSingleBsPresenterImpl();
    }

    @Override
    public void onSampleDataSuccess() {

    }

    @Override
    public void onSampleDataError() {

    }

}
