package com.zhidiantech.orangeframe.support.exception;

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
 * Create: 2019/1/3 上午11:42
 * </p>
 *
 * <p>
 * Changes (from 2019/1/3)
 * </p>
 *
 * <p>
 * 初始化异常类
 * </p>
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public class OrangeFrameInitError extends Error {
    /**
     * 构造方法
     *
     * @param s 异常信息
     */
    public OrangeFrameInitError(String s) {
        super(s);
    }
}
