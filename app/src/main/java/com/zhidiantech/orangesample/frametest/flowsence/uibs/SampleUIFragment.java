package com.zhidiantech.orangesample.frametest.flowsence.uibs;

import android.view.View;

import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;
import com.zhidiantech.orangeframe.support.ui.OrangeFrameFragment;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 上午11:37
 * Changes (from 2019/1/3)
 * Fragment示例
 * -----------------------------------------------------------------
 */
public class SampleUIFragment extends OrangeFrameFragment{
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected boolean isNeedTitleBar() {
        return false;
    }

    @Override
    protected boolean isBindButterKnife() {
        return false;
    }

    @Override
    protected OrangeFramePresenter initPresenter() {
        return null;
    }

    @Override
    protected void handleCreateView(View rootView) {
        //在这里编写的初始化操作，此方法在生命周期onCreateView()中执行
    }
}
