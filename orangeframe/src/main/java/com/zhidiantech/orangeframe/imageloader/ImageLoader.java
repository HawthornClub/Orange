package com.zhidiantech.orangeframe.imageloader;

import android.content.Context;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/15 下午6:17
 * Changes (from 2019/1/15)
 * -----------------------------------------------------------------
 * 图片加载
 */
public class ImageLoader {

    public static BaseImageLoaderStrategy mStrategy;

    /**
     * 加载图片
     */
    public static  <T extends ImageConfig> void loadImage(Context context, T config) {
        if (mStrategy == null) {
            throw new
                    NullPointerException("you should invoke setLoadImgStrategy first");
        }
        mStrategy.displayImage(context,config);
    }

    //设置上你的策略实现类对象，让它去调自己的displayImage()方法
    public static void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public static BaseImageLoaderStrategy getLoadImgStrategy() {
        return mStrategy;
    }

}
