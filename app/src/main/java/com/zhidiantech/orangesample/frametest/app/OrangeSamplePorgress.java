package com.zhidiantech.orangesample.frametest.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;

import com.zhidiantech.orangeframe.support.progress.ICustomProgress;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/2 下午6:27
 * Changes (from 2019/1/2)
 * 进度条弹窗
 * -----------------------------------------------------------------
 */
public class OrangeSamplePorgress implements ICustomProgress {
    private ProgressDialog progressDialog;
    private String mHint;
    public OrangeSamplePorgress(String hint){
        mHint=hint;
    }
    @Override
    public void createUI(Context baseContext) {
        progressDialog = new ProgressDialog(baseContext);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        //点击外部是否可以取消
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("提示");
        progressDialog.setMessage(mHint);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void showUI() {
        progressDialog.show();
    }

    @Override
    public void hideUI() {
        progressDialog.hide();
    }
}
