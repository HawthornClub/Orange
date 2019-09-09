package com.zhidiantech.orangeframe.easyhttp.http;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 下午3:01
 * Changes (from 2019/1/14)
 * <p>retrofit配置
 * -----------------------------------------------------------------
 */
public class RetrofitClient {
    private static RetrofitClient MINSTANCE;
    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOKHttpBuilder;

    private RetrofitClient(){
        if (mOKHttpBuilder==null){
            mOKHttpBuilder=new OkHttpClient.Builder();
        }
        mRetrofitBuilder=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static RetrofitClient getInstance(){
        if (MINSTANCE==null){
            synchronized (RetrofitClient.class){
                if (MINSTANCE==null){
                    MINSTANCE=new RetrofitClient();
                }
            }
        }
        return MINSTANCE;
    }

    /**
     * 获取Retrofit Builder
     * @return
     */
    public Retrofit.Builder getRetrofitBuilder(){
        return mRetrofitBuilder;
    }


    /**
     * 获取Retrofit Builder
     * @return
     */
    public OkHttpClient.Builder getOkHttpBuilder(){
        return mOKHttpBuilder;
    }

    /**
     * 构建Retrofit
     * @return
     */
    public Retrofit buildRetrofit(){
        return mRetrofitBuilder.client(mOKHttpBuilder.build()).build();
    }
}
