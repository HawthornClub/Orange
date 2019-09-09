package com.zhidiantech.orangeframe.support.progress;

import android.content.Context;

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
 * Create: 2019/1/2 下午6:23
 * </p>
 *
 * <p>
 * Changes (from 2019/1/2)
 * </p>
 *
 * <p>
 * 对外提供的自定义进度条接口
 * </p>
 *
 * <p>
 * 项目中自定义加载框需实现这个接口
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public interface ICustomProgress {
    /**
     * 创建UI
     * @param baseContext 上下文
     */
    void createUI(Context baseContext);

    /**
     * 显示UI
     */
    void showUI();

    /**
     * 隐藏UI
     */
    void hideUI();
}
