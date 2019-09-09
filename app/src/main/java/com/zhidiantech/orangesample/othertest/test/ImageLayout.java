package com.zhidiantech.orangesample.othertest.test;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zhidiantech.orangeframe.imageloader.ImageConfigImpl;
import com.zhidiantech.orangeframe.imageloader.ImageLoader;
import com.zhidiantech.orangeframe.util.density.DensityUtil;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/23 下午5:46
 * Changes (from 2019/1/23)
 * -----------------------------------------------------------------
 */
public class ImageLayout extends FrameLayout implements View.OnTouchListener {

    private Context mContext;
    private View onTouchView;

    int startX;
    int startY;
    int startTouchViewLeft = 0;
    int startTouchViewTop = 0;

    public ImageLayout(@NonNull Context context) {
        super(context);
    }

    public ImageLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setOnTouchListener(this);
    }

    public void addImage(String img, int width, int height) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.loadImage(getContext(), ImageConfigImpl.builder().imageView(imageView).url(img).build());
        LayoutParams params = new LayoutParams(DensityUtil.dp2px(width), DensityUtil.dp2px(height));
        params.gravity = Gravity.CENTER;
        this.addView(imageView, params);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onTouchView = null;
                onTouchView = view;
                startX = (int) event.getX();
                startY = (int) event.getY();
                if (hasChildView(startX, startY)) {
                    startTouchViewLeft = onTouchView.getLeft();
                    startTouchViewTop = onTouchView.getTop();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                moveChildView((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                onTouchView = null;
                break;
        }
        return false;
    }

    private boolean hasChildView(int x, int y) {
        //循环获取子view，判断xy是否在子view上，即判断是否按住了子view
        for (int index = 0; index < this.getChildCount(); index++) {
            View view = this.getChildAt(index);
            int left = (int) view.getX();
            int top = (int) view.getY();
            int right = view.getRight();
            int bottom = view.getBottom();
            Rect rect = new Rect(left, top, right, bottom);
            boolean contains = rect.contains(x, y);
            //如果是与子view重叠则返回真
            if (contains) {
                onTouchView = view;
                onTouchView.bringToFront();
                return true;
            }

        }
        onTouchView = null;
        return false;
    }

    private void moveChildView(int x, int y) {
        if (onTouchView == null) {
            return;
        }
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = x - startX + startTouchViewLeft;
        params.topMargin = y - startY + startTouchViewTop;
        //限制子控件移动必须在视图范围内
        if (params.leftMargin < 0 || (params.leftMargin + onTouchView.getWidth()) > getWidth()) {
            params.leftMargin = onTouchView.getLeft();
        }
        if (params.topMargin < 0 || (params.topMargin + onTouchView.getHeight()) > getHeight()) {
            params.topMargin = onTouchView.getTop();
        }
        //移动时将是向左滑动 还是向右滑动 赋值PictureTagView
        onTouchView.setLayoutParams(params);
    }
}
