package com.zhidiantech.orangeframe.easyhttp.observer;

import android.support.annotation.NonNull;

import com.zhidiantech.orangeframe.BuildConfig;
import com.zhidiantech.orangeframe.easyhttp.exception.ApiException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 下午1:45
 * Changes (from 2019/1/14)
 * <p>线程切换工具,减少重复代码
 * <P>
 * <p>使用示例：
 * <p>在ModelImpl中
 * <p>mUpdateService.getAppUpdate()
 * <p>              .retryWhen(TransformControl.netRetry())
 * <p>             .compose(TransformControl.switchSchedulers())
 * <p>             .subscribe(observer);
 *
 * -----------------------------------------------------------------
 */
public class TransformControl {
    /**
     * 是否为开发环境
     */
    private static boolean DEBUG_SCENE;

    /**
     * 设置环境来标记是否捕获异常
     * @param buildConfigDebug 项目中BuildConfig.Debug
     */
    public static void setDebugScene(boolean buildConfigDebug){
        DEBUG_SCENE=buildConfigDebug;
    }

    /**
     * 网络错误重试变量
     */

    //当前重试次数
    private static int mCurrentCount=0;
    //最大重试数，1默认值
    private static int mMaxCount=1;
    //等待重试的间隔时间
    private static int mWaitTime=0;

    /**
     * 错误重试方法
     * @return
     */
    public static Function<Observable<Throwable>, ObservableSource<?>> netRetry(){
        return createRetry();
    }

    /**
     * 网络错误重试 ，可以配置最大次数
     * @param maxCount 最大次数
     * @return
     */
    public static Function<Observable<Throwable>, ObservableSource<?>> netRetryMax(int maxCount){
        mMaxCount=maxCount;
        return createRetry();
    }

    private static Function<Observable<Throwable>, ObservableSource<?>> createRetry(){
        return new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof IOException){
                            if (mCurrentCount<mMaxCount){
                                mCurrentCount++;
                                mWaitTime=1000+mCurrentCount*1000; //事件间隔越来越大
                                return Observable.just(1).delay(mWaitTime,TimeUnit.MILLISECONDS);
                            }else {
                                return Observable.error(new Throwable("重试次数已超过最大次数"));
                            }
                        }
                        String errorInfo=ApiException.handleException(throwable).getMessage();
                        if (DEBUG_SCENE){
                            return Observable.error(throwable);
                        }else {
                            return Observable.error(new Throwable(errorInfo));
                        }
                    }
                });
            }
        } ;
    }

    /**
     * 线程切换
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
