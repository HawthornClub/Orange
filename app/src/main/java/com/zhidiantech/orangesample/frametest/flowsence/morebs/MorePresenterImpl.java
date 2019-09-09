package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;
import com.zhidiantech.orangesample.frametest.api.base.BaseResponse;
import com.zhidiantech.orangesample.frametest.api.sampleapi.SampleResponse;
import com.zhidiantech.orangesample.frametest.base.BaseObserver;

import io.reactivex.disposables.Disposable;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午3:51
 * Changes (from 2019/1/3)
 * -----------------------------------------------------------------
 */
public class MorePresenterImpl extends OrangeFramePresenter<MoreContract.IView> implements MoreContract.IPresenter {
    private MoreContract.IModel mModel;

    public MorePresenterImpl(){
        mModel=new MoreModelImpl();
    }

    @Override
    public void getMoreBusiness() {
        BaseObserver<BaseResponse<SampleResponse>> observer=new BaseObserver<BaseResponse<SampleResponse>>() {
            @Override
            protected void _onSub(Disposable d) {
                registerThing(d);
            }

            @Override
            protected void _onNext(BaseResponse<SampleResponse> sampleResponseBaseResponse) {
                if (sampleResponseBaseResponse.getCode()==200){
                    getView().onMoreBusinessSuccess(sampleResponseBaseResponse.getData());
                }else {
                    getView().onMoreBusinessError();
                }
            }

            @Override
            protected void _onError(String errorMsg) {
                getView().onMoreBusinessError();
            }

            @Override
            protected void showDialog() {
                getView().showProgressIo();
            }

            @Override
            protected void hideDialog() {
                getView().hideProgressIo();
            }
        };
        mModel.getMoreBusinessData(observer,"id");
    }
}
