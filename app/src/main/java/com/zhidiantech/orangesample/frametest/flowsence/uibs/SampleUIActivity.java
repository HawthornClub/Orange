package com.zhidiantech.orangesample.frametest.flowsence.uibs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;
import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;
import com.zhidiantech.orangesample.R;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2018/12/29 下午4:34
 * Changes (from 2018/12/29)
 * 纯UI无业务页面
 * -----------------------------------------------------------------
 */
public class SampleUIActivity extends OrangeFrameActivity {

    @Override
    protected boolean isNeedTitleBar() {
        return false;
    }

    @Override
    protected boolean isBindButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample_ui;
    }

    @Override
    protected OrangeFramePresenter initPresenter() {
        return null;
    }

    private String[] mLayoutKey={"无收藏数据","无网络页面","其他页面"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOtherView("无收藏数据", R.layout.no_collect_layout);
        View noCollectLayout = getContentByKey("无收藏数据页面");

        initOtherViews(mLayoutKey,R.layout.no_collect_layout,R.layout.net_error,R.layout.no_data);
        View netErrorLayout = getContentByKey(mLayoutKey[1]);

        showOtherView("无收藏数据页面");
        showOtherView(mLayoutKey[0]);

        showCoreContentView();

        showHttpErrorView();

        View httpView=getHttpErrorView();
    }
}
