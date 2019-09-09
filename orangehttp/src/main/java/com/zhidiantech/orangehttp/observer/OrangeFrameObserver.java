package com.zhidiantech.orangehttp.observer;


import com.zhidiantech.orangehttp.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 上午11:43
 * Changes (from 2019/1/14)
 * <p>HTTP数据请求观察者</p>
 * <p>可继承扩展</p>
 * <p>子类继承父类，需要保持其泛型化</p>
 * -----------------------------------------------------------------
 */
public abstract class OrangeFrameObserver <T> implements Observer<T> {
    public OrangeFrameObserver(){}

    @Override
    public void onError(Throwable e) {
        //JSON结构不同时，处理Crash在内部
        String error = ApiException.handleException(e).getMessage();
        hideDialog();
        _onError(error);
    }

    @Override
    public void onSubscribe(Disposable d) {
        _onSub(d);
        showDialog();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onComplete() {
        hideDialog();
    }


    protected abstract void _onSub(Disposable d);

    protected abstract void _onNext(T t);

    protected abstract void _onError(String errorMsg);

    /**
     * 进度条或对话框
     */
    protected abstract void showDialog();

    protected abstract void hideDialog();
}
