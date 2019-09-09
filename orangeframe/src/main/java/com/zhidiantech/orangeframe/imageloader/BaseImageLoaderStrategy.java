package com.zhidiantech.orangeframe.imageloader;

import android.content.Context;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/15 下午4:50
 * Changes (from 2019/1/15)
 * -----------------------------------------------------------------
 */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    //定义供外部调用的显示图片的方法
    void displayImage(Context context, T config);
}
