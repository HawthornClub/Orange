package com.zhidiantech.orangeframe.support.mvp;

import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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
 * Create: 2018/12/25 下午4:59
 * </p>
 *
 * <p>
 * Changes (from 2018/12/25)
 * </p>
 *
 * <p>
 * 业务Presenter实现类需继承
 * </p>
 *
 * <p>
 * 控制P与V的注入与解绑
 * </p>
 *
 * <p>
 * {@link OrangeFrameActivity}
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public class OrangeFramePresenter<V extends IOrangeFrameView> {

    private WeakReference<V> mViewRef;
    /**
     * 事件队列
     */
    private CompositeDisposable mAllDisposable=new CompositeDisposable();

    /**
     * 关联V层
     * @param pView View
     */
    public void onAttachView(V pView){
        mViewRef=new WeakReference<>(pView);
    }

    /**
     * 解除与V的关联
     */
    public void onDetachView(){
        clearThing();
        if (null!=mViewRef){
            mViewRef.clear();
            mViewRef=null;
        }
    }

    /**
     * protected 权限符控制事件订阅 业务层执行,不对外开放
     * @param disposable rx事件
     */
    protected void registerThing(Disposable disposable){
        mAllDisposable.add(disposable);
    }

    /**
     * protected 权限符控制事件取消 业务层执行,不对外开放
     * @param disposable rx事件
     */
    protected void unRegisterThing(Disposable disposable){
        mAllDisposable.remove(disposable);
    }

    /**
     * 清除所有订阅事件
     */
    private void clearThing(){
        mAllDisposable.clear();
    }

    /**
     * 是否关联
     * @return
     */
    private boolean isAttach(){
        return null!=mViewRef && null!=mViewRef.get();
    }

    /**
     * 获取View引用
     * @return 返回V的弱引用
     */
    protected V getView(){
        if (isAttach()){
            return mViewRef.get();
        }
        return null;
    }
}
