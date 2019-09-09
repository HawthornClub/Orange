package com.zhidiantech.orangeframe.support.progress;

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
 * Create: 2019/1/2 下午4:11
 * </p>
 *
 * <p>
 * Changes (from 2019/1/2)
 * </p>
 *
 * <p>
 * 进度条设置接口
 * </p>
 *
 * <p>
 * 在项目的APP中实现
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public interface IProgressSetting {
    /**
     * 配置自定义的进入页面进度框
     * Page
     */
    ICustomProgress initPageCustomProgress();

    /**
     * 配置自定义的加载框
     * IO 网络
     * @return
     */
    ICustomProgress initIoCustomProgress();
}
