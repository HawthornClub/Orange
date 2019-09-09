package com.zhidiantech.orangeframe.support.component.dialog;

import android.view.View;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/3/25 下午4:26
 * Changes (from 2019/3/25)
 * 弹窗拥有的基本能力
 * -----------------------------------------------------------------
 */
public interface DialogPower {
    /**
     * 打开弹窗
     */
    void showDialog();

    /**
     * 关闭弹窗
     */
    void closeDialog();

    /**
     * 设置处理弹窗内部UI
     */
    void setUIHandler(UIHandler handler);


    interface UIHandler{
        void handleUI(View viewLayout);
    }

    //布局资源
    String LAYOUTRES="layoutRes";
    //宽度百分比
    String WIDTHP="widthP";
    //高度百分比
    String HEIGHTP="heightP";
    //是否可以阴影点击关闭
    String OUTCANCEL="outCancel";
    //位置
    String GRAVITY="gravity";
    //动画类型 0为Orange内置 >0 则获取自定义动画资源
    String ANIM="anim";
    //动画资源
    String ANIMRES="animRes";
    //保存弹窗状态
    String SAVESTATE="savestate";
}
