package com.zhidiantech.orangeframe.support.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhidiantech.orangeframe.R;
import com.zhidiantech.orangeframe.util.density.DensityUtil;

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
 * Create: 2018/12/24 下午4:41
 * </p>
 *
 * <p>
 * Changes (from 2018/12/24)
 * </p>
 *
 * <p>
 * 标题栏
 * </p>
 *
 * <p>
 * 基于ToolBar
 * </p>
 *
 * <p>
 * 提供常用的 icon-title-icon  icon-icon 布局
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public class TitleBar extends FrameLayout{

    /**
     * toolbar
     */
    private Toolbar mToolBar;

    /**
     * 右边的布局容器
     */
    private LinearLayout mRightll;
    /**
     * 标题
     */
    private TextView mTitle;
    /**
     * 右边的Tv
     */
    private TextView mTvRight;
    /**
     * 右边的Icon
     */
    private ImageView mRightIcon;
    /**
     * 左边的Icon
     */
    private ImageView mLeftIcon;

    public TitleBar(@NonNull Context context) {
        super(context);
        initTitleBar();
    }

    /**
     * 初始化控件
     */
    private void initTitleBar() {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.frame_titlebar_layout,this,true);
        mToolBar=view.findViewById(R.id.tl);

        mRightll=view.findViewById(R.id.ll_right);

        mTitle=view.findViewById(R.id.tv_title);

        mTvRight=view.findViewById(R.id.tv_right);

        mRightIcon=view.findViewById(R.id.iv_right);

        mLeftIcon=view.findViewById(R.id.iv_left);

    }

    /**
     * 设置toolbar背景颜色
     * @param drawable
     * @return
     */
    public TitleBar setTitleBarBackground(Drawable drawable){
        mToolBar.setBackground(drawable);
        return this;
    }
    public TitleBar setTitleBarBackgroundRes(@DrawableRes int resId){
        mToolBar.setBackgroundResource(resId);
        return this;
    }
    public TitleBar setTitleBarBackground(@ColorInt int color){
        mToolBar.setBackgroundColor(color);
        return this;
    }

    /**
     * Toolbar
     * 设置标题栏左边的图标
     * @param icon
     */
    public TitleBar setToolBarLeftIcon(Drawable icon){
        mToolBar.setNavigationIcon(icon);
        return this;
    }

    /**
     * Toolbar
     * 设置标题栏左边的图标
     * @param icon
     */
    public TitleBar setToolBarLeftIcon(@DrawableRes int icon){
        mToolBar.setNavigationIcon(icon);
        return this;
    }

    /**
     * 设置标题
     * @param title
     */
    public TitleBar setTitle(String title){
        mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题的颜色
     * @param color
     */
    public TitleBar setTitleColor(@ColorInt int color){
        mTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置标题栏加粗
     * @return
     */
    public TitleBar setTitleBold(){
        mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        return this;
    }

    /**
     * 设置文字大小
     * @param sp
     * @return
     */
    public TitleBar setTitleSize(int sp){
        mTitle.setTextSize(sp);
        return this;
    }

    /**
     * 设置右边文字
     * @param str
     */
    public TitleBar setRightTv(String str){
        if (mTvRight.getVisibility()==GONE){
            mTvRight.setVisibility(VISIBLE);
        }
        mTvRight.setText(str);
        return this;
    }

    /**
     * 设置右边文字的颜色
     * @param color
     */
    public TitleBar setRightTvColor(@ColorInt int color){
        mTvRight.setTextColor(color);
        return this;
    }

    /**
     * 设置右边文字
     * 默认为10dp
     * @param dp
     * @return
     */
    public TitleBar setRightTvMarginRight(int dp){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                mTvRight.getLayoutParams());
        lp.setMargins(0, 0, DensityUtil.dp2px(dp), 0);//4个参数按顺序分别是左上右下
        lp.gravity=Gravity.CENTER;
        mTvRight.setLayoutParams(lp);
        return this;
    }
    /**
     * 图标的右边距
     * 默认为10dp
     * @param dp
     * @return
     */
    public TitleBar setRightIconMarginRight(int dp){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                mRightIcon.getLayoutParams());
        lp.setMargins(0, 0, DensityUtil.dp2px(dp), 0);//4个参数按顺序分别是左上右下
        lp.gravity=Gravity.CENTER;
        mRightIcon.setLayoutParams(lp);
        return this;
    }

    /**
     * 设置左边的图标
     * 右边距默认为10dp
     * @param resIcon icon资源
     */
    public TitleBar setLeftIconDrawable(@DrawableRes int resIcon){
        if (mLeftIcon.getVisibility()==GONE){
            mLeftIcon.setVisibility(VISIBLE);
        }
        mLeftIcon.setImageResource(resIcon);
        return this;
    }

    /**
     * 设置左边图标的大小
     * @param widthDp
     * @param heightDp
     * @return
     */
    public TitleBar setLeftIconSize(int widthDp,int heightDp){
        ViewGroup.LayoutParams lp = mLeftIcon.getLayoutParams();
        lp.width=DensityUtil.dp2px(widthDp);
        lp.height=DensityUtil.dp2px(heightDp);
        mLeftIcon.setLayoutParams(lp);
        return this;
    }

    /**
     * 设置右边的图标
     * 右边距默认为10dp
     * @param resIcon icon资源
     */
    public TitleBar setRightIconDrawable(@DrawableRes int resIcon){
        if (mRightIcon.getVisibility()==GONE){
            mRightIcon.setVisibility(VISIBLE);
        }
        mRightIcon.setImageResource(resIcon);
        return this;
    }

    /**
     * 设置右边图标的大小
     * @param widthDp
     * @param heightDp
     * @return
     */
    public TitleBar setRightIconSize(int widthDp,int heightDp){
        ViewGroup.LayoutParams lp = mRightIcon.getLayoutParams();
        lp.width=DensityUtil.dp2px(widthDp);
        lp.height=DensityUtil.dp2px(heightDp);
        mRightIcon.setLayoutParams(lp);
        return this;
    }

    /**
     * 设置标题栏图标的点击事件
     */
    public TitleBar setIconClick(TitleBarListener listener){
        if (mClickListener==null){
            mClickListener=listener;
        }
        mLeftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onLeftIconClick();
            }
        });

        mRightll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onRightIconTvClick();
            }
        });
        return this;
    }

    /**
     * 设置ToolBar图标的点击事件
     */
    public TitleBar setToolBarClick(ToolBarListener listener){
        if (mToolBarClickListener==null){
            mToolBarClickListener=listener;
        }
        mToolBar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolBarClickListener.onNaIconClick();
            }
        });
        return this;
    }



    private TitleBarListener mClickListener;

    /**
     * TitleBar事件
     */
    public interface TitleBarListener{
        /**
         * 左边按钮点击事件
         */
        void onLeftIconClick();

        /**
         * 右边按钮点击事件
         */
        void onRightIconTvClick();
    }

    private ToolBarListener mToolBarClickListener;

    /**
     * ToolBar事件
     */
    public interface ToolBarListener{
        /**
         * 按钮点击事件
         */
        void onNaIconClick();

    }

}
