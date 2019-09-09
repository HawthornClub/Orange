package com.zhidiantech.orangeframe.support.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zhidiantech.orangeframe.support.exception.OrangeFrameInitError;

import java.util.HashMap;
import java.util.Map;

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
 * Create: 2018/12/21 下午5:58
 * </p>
 *
 * <p>
 * Changes (from 2018/12/21)
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 *
 */
public class ContentLayout extends FrameLayout{
    /**
     * 主内容
     */
    private View mContentView;

    /**
     * 需要显示的其他View集合
     */
    private Map<String,View> mShowViewMap;

    public ContentLayout(@NonNull Context context) {
        super(context);
    }

    /**
     * 设置ContentView
     * @param layoutRes layout文件
     */
    public void setContentView(@LayoutRes int layoutRes){
        View view=LayoutInflater.from(getContext()).inflate(layoutRes,this,false);
        setContentView(view);
    }

    /**
     * 设置ContentView
     * @param layoutView layoutView
     */
    public void setContentView(View layoutView){
        mContentView=layoutView;
        //加载完成后添加到FrameLayout
        addView(mContentView);
    }

    /**
     * 检查资源文件的正确性
     * @return
     */
    private boolean checkRes(int layoutRes){
        return !TextUtils.isEmpty(getContext().getResources().getResourceName(layoutRes));
    }

    /**
     * 检查所有资源文件的正确性
     * @return
     */
    private boolean checkRes(int... layoutRes){
        boolean result=true;
        for (int layoutRe : layoutRes) {
            if (TextUtils.isEmpty(getContext().getResources().getResourceName(layoutRe))) {
                result=false;
                break;
            }
        }
        return result;
    }

    /**
     * 检查长度是否对应
     * @param layoutKeys key
     * @param layoutRes res
     * @return 返回长度是否对应
     */
    private boolean checkRight(String[] layoutKeys,int... layoutRes ){
        if (layoutKeys.length==layoutRes.length){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 显示指定内容
     * @param layoutKey 指定的key
     */
    public void showOtherView(String layoutKey) {
        if (mShowViewMap==null){
            throw new OrangeFrameInitError("请调用initOtherView初始化当前页面其他的展示视图！");
        }
        removeAllViews();
        addView(mShowViewMap.get(layoutKey));
    }

    /**
     * 显示核心的业务页面
     */
    public void showCoreContentView() {
        removeAllViews();
        addView(mContentView);
    }

    /**
     * 单页面添加
     * 添加指定的资源文件
     *
     * @param layoutKey 资源文件的名称
     * @param layoutRes 指定的资源文件
     */
    public void initOtherView(final String layoutKey,int layoutRes) {
        if (!checkRes(layoutRes)){
            throw new OrangeFrameInitError("请检查资源名称正确性");
        }
        if (mShowViewMap==null){
            mShowViewMap=new HashMap<>();
        }
        View showView=LayoutInflater.from(getContext()).inflate(layoutRes,this,false);
        mShowViewMap.put(layoutKey,showView);
    }

    /**
     * 多页面添加
     * key - layoutRes 一一对应
     *
     * @param layoutKeys key数组
     * @param layoutRes  layoutRes可变序列
     */
    public void initOtherViews(String[] layoutKeys, int... layoutRes) {
        if (!checkRes(layoutRes) || !checkRight(layoutKeys,layoutRes)){
            throw new OrangeFrameInitError("请检查资源名称正确性，或key-res长度是否对应");
        }

        if (mShowViewMap==null){
            mShowViewMap=new HashMap<>();
        }

        View itemView;
        int count=0;
        for (int layoutRe:layoutRes) {
            itemView=LayoutInflater.from(getContext()).inflate(layoutRe,this,false);
            mShowViewMap.put(layoutKeys[count],itemView);
            count++;
        }
    }

    /**
     * 获取指定key的View
     * @param key 指定的key
     * @return
     */
    public View getContentByKey(String key){
        return mShowViewMap.get(key);
    }
}
