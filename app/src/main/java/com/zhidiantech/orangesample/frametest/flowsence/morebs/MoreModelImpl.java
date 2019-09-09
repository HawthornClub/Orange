package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import com.zhidiantech.orangeframe.easyhttp.http.OfHttpUtil;
import com.zhidiantech.orangeframe.easyhttp.observer.TransformControl;
import com.zhidiantech.orangesample.frametest.api.OrangeSampleApiManager;
import com.zhidiantech.orangesample.frametest.api.base.BaseResponse;
import com.zhidiantech.orangesample.frametest.api.sampleapi.SampleResponse;
import com.zhidiantech.orangesample.frametest.base.BaseObserver;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午3:54
 * Changes (from 2019/1/3)
 * -----------------------------------------------------------------
 */
public class MoreModelImpl implements MoreContract.IModel {

    private OrangeSampleApiManager.SampleApi mSampleApi;

    public MoreModelImpl() {
        mSampleApi = OfHttpUtil.createApi(OrangeSampleApiManager.SampleApi.class);
    }

    @Override
    public void getMoreBusinessData(BaseObserver<BaseResponse<SampleResponse>> observer, String id) {
        mSampleApi.
                getSample(id)
                .compose(TransformControl.<BaseResponse<SampleResponse>>switchSchedulers())
                .subscribe(observer);
    }
}
