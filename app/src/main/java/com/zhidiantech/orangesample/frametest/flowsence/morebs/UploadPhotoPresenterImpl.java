package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午3:45
 * Changes (from 2019/1/3)
 * -----------------------------------------------------------------
 */
public class UploadPhotoPresenterImpl extends OrangeFramePresenter<UploadPhotoContract.IView> implements UploadPhotoContract.IPresenter {
    private UploadPhotoContract.IModel mModel;

    public UploadPhotoPresenterImpl(){
        mModel=new UploadPhotoModelImpl();
    }

    @Override
    public void uploadPhoto() {
        mModel.uploadPhotoFile();
    }
}
