package com.zhidiantech.orangeframe.support.component;


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

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2018/12/26 下午4:56
 * Changes (from 2018/12/26)
 * 弹窗
 * 默认为丛下到上弹窗
 * -----------------------------------------------------------------
 */
@Deprecated
public class PushDialog extends DialogFragment {
    //宽度百分比
    private float mWidthPrecent=1.0f;
    //高度百分比
    private float mHeightPrecent=0.8f;
    //默认点击外部可以关闭弹窗
    private boolean mOutCancel=true;
    //位置,默认下中
    private int mGravity=0;
    public PushDialog() {
        // Required empty public constructor
    }

    /**
     * 创建弹窗
     * @param layoutId
     * @param outCancel 点击外部是否可以取消
     * @return
     */
    public static PushDialog createDialog(@LayoutRes int layoutId,boolean outCancel) {
        PushDialog fragment = new PushDialog();
        Bundle bundle=new Bundle();
        bundle.putInt("layout",layoutId);
        bundle.putBoolean("outCancel",outCancel);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 创建弹窗
     * @param layoutId
     * @param gravity 位置
     */
    public static PushDialog createDialog(@LayoutRes int layoutId,boolean outCancel,int gravity) {
        PushDialog fragment = new PushDialog();
        Bundle bundle=new Bundle();
        bundle.putInt("layout",layoutId);
        bundle.putBoolean("outCancel",outCancel);
        bundle.putInt("gravity",gravity);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 创建弹窗
     * @param layoutId
     * @param widthPrecent  宽度比例
     * @param heightPrecent  高度比例
     * @param outCancel 点击外部是否可以取消
     * @return
     */
    public static PushDialog createDialog(@LayoutRes int layoutId,float widthPrecent,float heightPrecent,boolean outCancel) {
        PushDialog fragment = new PushDialog();
        Bundle bundle=new Bundle();
        bundle.putInt("layout",layoutId);
        bundle.putFloat("widthP",widthPrecent);
        bundle.putFloat("heightP",heightPrecent);
        bundle.putBoolean("outCancel",outCancel);
        fragment.setArguments(bundle);
        return fragment;
    }
    /**
     * 创建弹窗
     * @param layoutId
     * @param widthPrecent  宽度比例
     * @param heightPrecent  高度比例
     * @param gravity 位置
     * @return
     */
    public static PushDialog createDialog(@LayoutRes int layoutId,float widthPrecent,float heightPrecent,boolean outCancel,int gravity) {
        PushDialog fragment = new PushDialog();
        Bundle bundle=new Bundle();
        bundle.putInt("layout",layoutId);
        bundle.putFloat("widthP",widthPrecent);
        bundle.putFloat("heightP",heightPrecent);
        bundle.putBoolean("outCancel",outCancel);
        bundle.putInt("gravity",gravity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 为获取屏幕宽、高
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        if (mHeightPrecent==0.0){
            lp.height = (int) (d.getHeight() * 0.8) ;
        }else {
            lp.height =  (int) (d.getHeight() * mHeightPrecent);
        }
        if (mWidthPrecent==0.0){
            lp.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        }else {
            lp.width = (int) (d.getWidth() *mWidthPrecent);
        }
        getDialog().getWindow().setAttributes(lp);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId=0;
        if (getArguments()!=null){
            layoutId=getArguments().getInt("layout");
            mWidthPrecent=getArguments().getFloat("widthP");
            mHeightPrecent=getArguments().getFloat("heightP");
            mOutCancel=getArguments().getBoolean("outCancel");
            mGravity=getArguments().getInt("gravity");
        }
        View view = inflater.inflate(layoutId, container, false);

        //背景动画
        getDialog().getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();

        //设置位置
        if (mGravity==0 ||mGravity==GRAVITY_BOTTOM){
            lp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        }else if (mGravity==GRAVITY_CENTER){
            lp.gravity = Gravity.CENTER;
        }else if (mGravity==GRAVITY_TOP){
            lp.gravity=Gravity.TOP|Gravity.CENTER_HORIZONTAL;
        }
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        lp.windowAnimations = R.style.pushDialogAnim;
        getDialog().getWindow().setAttributes(lp);
        getDialog().setCanceledOnTouchOutside(mOutCancel);
        getDialog().setCancelable(mOutCancel);

        if (mLisenter!=null){
            mLisenter.handleUI(view);
        }
        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 显示弹窗
     * @param fragmentManager
     */
    public void showDialog(FragmentManager fragmentManager){
        if (this.isAdded()){
            this.show(fragmentManager,this.getTag());
        }else {
            fragmentManager.beginTransaction().add(this,this.getTag()).commitNowAllowingStateLoss();
        }
    }

    private ViewListener mLisenter;

    public interface ViewListener{
        void handleUI(View view);
    }

    /**
     * 设置View事件接口
     * @param lisenter
     */
    public void setViewLisenter(ViewListener lisenter){
        mLisenter=lisenter;
    }

    /**
     * 关闭弹窗
     */
    public void closeDialog(){
        this.dismiss();
    }

    public static int GRAVITY_TOP=1;
    public static int GRAVITY_BOTTOM=2;
    public static int GRAVITY_CENTER=3;
}

