package com.zhidiantech.orangesample.frametest.flowsence.singlebs;


import com.zhidiantech.orangesample.frametest.base.IBaseView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2018/12/29 下午5:03
 * Changes (from 2018/12/29)
 * MVP管理
 * -----------------------------------------------------------------
 */
public interface SampleSingleBsContract {
    interface IPresenter {

        void getSampleData();
    }

    interface IView extends IBaseView {
        void onSampleDataSuccess();

        void onSampleDataError();
    }

    interface IModel {

    }
}
