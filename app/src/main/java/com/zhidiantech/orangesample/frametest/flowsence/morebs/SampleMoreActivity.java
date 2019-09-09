package com.zhidiantech.orangesample.frametest.flowsence.morebs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;
import com.zhidiantech.orangeframe.support.widget.TitleBar;
import com.zhidiantech.orangesample.R;
import com.zhidiantech.orangesample.frametest.api.sampleapi.SampleResponse;
import com.zhidiantech.orangesample.frametest.flowsence.singlebs.SampleSingleBsActivity;

import butterknife.BindView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/3 下午3:48
 * Changes (from 2019/1/3)
 * 多业务场景
 * -----------------------------------------------------------------
 */
public class SampleMoreActivity extends OrangeFrameActivity<MorePresenterImpl> implements MoreContract.IView,UploadPhotoContract.IView {
    private UploadPhotoPresenterImpl mUploadPresenter=new UploadPhotoPresenterImpl();

    @BindView(R.id.bt_open_single)
    Button mOpenSingle;
    @Override
    protected boolean isNeedTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setTitleBarBackground(getResources().getColor(R.color.colorPrimaryDark))
        .setTitle("多业务页面");
    }

    @Override
    protected boolean isBindButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    protected MorePresenterImpl initPresenter() {
        return new MorePresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getMoreBusiness(); //主业务
        mUploadPresenter.uploadPhoto();//其他业务，需要注册与解除注册
        mOpenSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SampleMoreActivity.this,SampleSingleBsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onAttachMoreView() {
        super.onAttachMoreView();
        mUploadPresenter.onAttachView(this);
    }

    @Override
    protected void onDetachMoreView() {
        super.onDetachMoreView();
        mUploadPresenter.onDetachView();
    }

    @Override
    public void onMoreBusinessSuccess(SampleResponse response) {
        //do something...
    }

    @Override
    public void onMoreBusinessError() {

    }

    @Override
    public void onUploadPhotoSuccess() {

    }

    @Override
    public void onUploadPhotoError() {

    }

}
