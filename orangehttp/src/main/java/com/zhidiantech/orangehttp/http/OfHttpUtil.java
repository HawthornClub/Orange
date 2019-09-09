package com.zhidiantech.orangehttp.http;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 下午6:05
 * Changes (from 2019/1/14)
 * <p>网络请求入口
 * -----------------------------------------------------------------
 */
import android.app.Application;
import android.content.Context;

public class OfHttpUtil {
    private static Application mContext;
    private static OfHttpUtil mInstance;

    /**
     * 初始化Http组件
     * @param app
     */
    public static void init(Application app){
        mContext=app;
    }

    /**
     * 获取实例
     * @return
     */
    public static OfHttpUtil getInstance(){
        if (mContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 OfHttpUtil.init() 初始化！");
        }
        if (mInstance==null){
            synchronized (OfHttpUtil.class){
                if (mInstance==null){
                    mInstance=new OfHttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 开启配置
     * @return
     */
    public HttpAllocator startConfig(){
        return HttpAllocator.getInstance();
    }

    /**
     * 创建业务Api
     * @param cls
     * @param <K>
     * @return
     */
    public static <K> K createApi(Class<K> cls){
        return HttpAllocator.createApi(cls);
    }

    /**
     * 获取Context
     * @return
     */
    public static Context getContext(){
        if (mContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 OfHttpUtil.init() 初始化！");
        }
        return mContext;
    }

}


