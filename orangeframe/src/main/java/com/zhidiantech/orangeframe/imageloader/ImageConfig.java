package com.zhidiantech.orangeframe.imageloader;

import android.widget.ImageView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/2 下午8:59
 * Changes (from 2019/1/2)
 * -----------------------------------------------------------------
 */
public class ImageConfig {
    protected String url;
    protected ImageView imageView;
    protected int placeholder;//占位符
    protected int errorPic;//错误占位符

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }
}
