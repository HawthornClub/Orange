package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import com.zhidiantech.orangesample.frametest.api.base.BaseResponse;
import com.zhidiantech.orangesample.frametest.api.sampleapi.SampleResponse;
import com.zhidiantech.orangesample.frametest.base.BaseObserver;
import com.zhidiantech.orangesample.frametest.base.IBaseView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午12:54
 * Changes (from 2019/1/3)
 * -----------------------------------------------------------------
 */
public interface MoreContract {
    interface IPresenter {
        void getMoreBusiness();
    }

    interface IView extends IBaseView{
        void onMoreBusinessSuccess(SampleResponse response);
        void onMoreBusinessError();
    }

    interface IModel{
        void getMoreBusinessData(BaseObserver<BaseResponse<SampleResponse>> observer ,
                                 String id);
    }
}
