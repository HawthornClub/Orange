package com.zhidiantech.orangeframe.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhidiantech.orangeframe.util.density.DensityUtil;

import java.util.ArrayList;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/15 下午4:51
 * Changes (from 2019/1/15)
 * -----------------------------------------------------------------
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<ImageConfigImpl> {
    @Override
    public void displayImage(Context context, ImageConfigImpl config) {
        RequestManager request = Glide.with(context);
        RequestBuilder<Drawable> glideRequest = request.load(config.getUrl());
        RequestOptions requestOptions = new RequestOptions();
        ArrayList<Transformation<Bitmap>> transformations = new ArrayList<>();
        switch (config.getCacheStrategy()) {//缓存策略
            case 0:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case 3:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 4:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            default:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
        }
        if (config.isCrossFade()) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade());
        }
        if (config.isCenterCrop()) {
            transformations.add(new CenterCrop());
        }

        if (config.isCircle()) {
            transformations.add(new CircleCrop());
        }

        if (config.isImageRadius()) {
            transformations.add(new RoundedCorners(DensityUtil.dp2px(config.getImageRadius())));
        }

        if (config.isBlurImage()) {
            transformations.add(new BlurTransformation(config.getBlurValue()));
        }

        if (config.getPlaceholder() != 0) {//设置占位符
            requestOptions.placeholder(config.getPlaceholder());
        }

        if (config.getErrorPic() != 0) {//设置错误的图片
            requestOptions.error(config.getErrorPic());
        }

        if (config.getFallback() != 0) {//设置请求 url 为空图片
            requestOptions.fallback(config.getFallback());
        }

        if (transformations.size()>0){
            Transformation<Bitmap>[] transArray = new Transformation[transformations.size()];
            for (int i = 0; i < transformations.size(); i++) {
                transArray[i] = transformations.get(i);
            }
            requestOptions.transforms(transArray);
        }
        glideRequest.apply(requestOptions);
        glideRequest.into(config.getImageView());
    }
}
