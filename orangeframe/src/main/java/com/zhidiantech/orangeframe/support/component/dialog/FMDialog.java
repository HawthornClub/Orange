package com.zhidiantech.orangeframe.support.component.dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zhidiantech.orangeframe.R;
import com.zhidiantech.orangeframe.support.component.PushDialog;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/3/25 下午4:34
 * Changes (from 2019/3/25)
 * -----------------------------------------------------------------
 */
public class FMDialog extends DialogFragment implements DialogPower {

    //位置,默认下中
    private int mGravity=0;
    //基于LayoutParams相对位置
    public static int GRAVITY_TOP=1;
    public static int GRAVITY_BOTTOM=2;
    public static int GRAVITY_CENTER=3;
    public static int GRAVITY_RIGHT = 4;

    //宽度百分比
    private float mWidthPercent=1.0f;
    //高度百分比
    private float mHeightPercent=0.8f;
    //默认点击外部可以关闭弹窗
    private boolean mOutCancel=true;
    //动画样式 0 push >0 其他
    private int mAnim = 0;
    private int mAnimRes=R.style.pushDialogAnim;

    //UI处理器
    private UIHandler mUiHandler;
    //FragmentManager管理器
    private FragmentManager mFragmentManager;
    @Override
    public void showDialog() {
        if (this.isAdded()){
            this.show(mFragmentManager,this.getTag());
        }else {
            mFragmentManager.beginTransaction().add(this,this.getTag()).commitNowAllowingStateLoss();
        }
    }

    @Override
    public void closeDialog() {
        this.dismiss();
    }

    @Override
    public void setUIHandler(UIHandler handler) {
        mUiHandler=handler;
    }

    //生命周期
    @Override
    public void onStart() {
        super.onStart();
        // 为获取屏幕宽、高
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        if (mHeightPercent==0.0){
            lp.height = (int) (d.getHeight() * 0.8) ;
        }else {
            lp.height =  (int) (d.getHeight() * mHeightPercent);
        }
        if (mWidthPercent==0.0){
            lp.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        }else {
            lp.width = (int) (d.getWidth() *mWidthPercent);
        }
        getDialog().getWindow().setAttributes(lp);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = 0;
        if (getArguments() != null) {
            layoutId = getArguments().getInt(DialogPower.LAYOUTRES);
            mWidthPercent = getArguments().getFloat(DialogPower.WIDTHP);
            mHeightPercent = getArguments().getFloat(DialogPower.HEIGHTP);
            mOutCancel = getArguments().getBoolean(DialogPower.OUTCANCEL);
            mGravity = getArguments().getInt(DialogPower.GRAVITY);
            mAnim = getArguments().getInt(DialogPower.ANIM);
            mAnimRes=getArguments().getInt(DialogPower.ANIMRES);
        }
        View view = inflater.inflate(layoutId, container, false);

        //背景动画
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        //设置位置
        if (mGravity == 0 || mGravity == GRAVITY_BOTTOM) {
            lp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        } else if (mGravity == GRAVITY_CENTER) {
            lp.gravity = Gravity.CENTER;
        } else if (mGravity == GRAVITY_TOP) {
            lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        } else if (mGravity == GRAVITY_RIGHT) {
            lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        }

        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        if (mAnim == 0) {
            lp.windowAnimations = R.style.pushDialogAnim;
        } else {
            lp.windowAnimations =mAnimRes;
        }
        getDialog().getWindow().setAttributes(lp);
        getDialog().setCanceledOnTouchOutside(mOutCancel);
        getDialog().setCancelable(mOutCancel);
        if (mUiHandler!=null){
            mUiHandler.handleUI(view);
        }
        return view;
    }

    /**
     * 设置管理器
     * @param fragmentManager
     */
    public void setFragmentManager(FragmentManager fragmentManager){
        mFragmentManager=fragmentManager;
    }

}
