package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import com.zhidiantech.orangesample.frametest.base.IBaseView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午12:54
 * Changes (from 2019/1/3)
 * -----------------------------------------------------------------
 */
public interface UploadPhotoContract {
    interface IPresenter {

        void uploadPhoto();
    }

    interface IView extends IBaseView{
        void onUploadPhotoSuccess();

        void onUploadPhotoError();
    }

    interface IModel{
        void uploadPhotoFile();
    }
}
