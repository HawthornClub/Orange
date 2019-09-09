package com.zhidiantech.orangeframe.support.mvp;

/**
 * <p>
 * -----------------------------------------------------------------
 * </p>
 *
 * <p>
 * Copyright (C) 芝点科技 wen
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 *
 * <p>
 * Create: 2018/12/25 下午5:00
 * </p>
 *
 * <p>
 * Changes (from 2018/12/25)
 * </p>
 *
 * <p>
 * 顶层view接口
 * </p>
 *
 * <p>
 * Activity在MVP中体验为V层，要做到在P层中拿到V的引用，需要通过接口的方式
 * </p>
 *
 * <p>
 * {@link OrangeFramePresenter}
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public interface IOrangeFrameView {

    /**
     * 显示加载框
     */
    void showProgressIo();

    /**
     * 隐藏加载框
     */
    void hideProgressIo();
}
