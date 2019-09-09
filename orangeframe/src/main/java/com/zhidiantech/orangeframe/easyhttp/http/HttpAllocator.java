package com.zhidiantech.orangeframe.easyhttp.http;


import com.zhidiantech.orangeframe.easyhttp.interceptor.HeaderInterceptor;
import com.zhidiantech.orangeframe.easyhttp.util.JsonFormat;
import com.zhidiantech.orangeframe.util.LogUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 下午5:30
 * Changes (from 2019/1/14)
 * <p>Http 构造器
 * -----------------------------------------------------------------
 */
public class HttpAllocator {
    public static final String TAG=HttpAllocator.class.getSimpleName();
    private static HttpAllocator mInstance;

    public static HttpAllocator getInstance() {
        if (mInstance == null) {
            synchronized (HttpAllocator.class) {
                if (mInstance == null) {
                    mInstance = new HttpAllocator();
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置base_url
     * @param baseUrl
     * @return
     */
    public HttpAllocator setBaseUrl(String baseUrl) {
        getGlobalRetrofitBuilder().baseUrl(baseUrl);
        return this;
    }

    /**
     * 移除请求头
     * @return
     */
    public HttpAllocator removeHeaders(List<String> headerNames) {
        HeaderInterceptor headerInterceptor=new HeaderInterceptor();
        headerInterceptor.removeHeaderName(headerNames);
        getGlobalOkHttpBuilder().addInterceptor(headerInterceptor);
        return this;
    }

    /**
     * 添加请求头
     * @param headers
     * @return
     */
    public HttpAllocator addHeader(Map<String,Object> headers){
        getGlobalOkHttpBuilder().addInterceptor(new HeaderInterceptor(headers));
        return this;
    }

    /**
     * 设置是否打印log,可在正式版时关闭
     * @param isShowLog 工程中BuildConfig.debug
     * @return
     */
    public HttpAllocator setLog(boolean isShowLog){
        if (isShowLog){
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                private StringBuilder mMessage = new StringBuilder();
                @Override
                public void log(String message) {
                    // 请求或者响应开始
                    if (message.startsWith("--> POST")) {
                        mMessage.setLength(0);
                    }
                    // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
                    boolean isMessage=(message.startsWith("{") && message.endsWith("}"));
                    if (isMessage || message.startsWith("[") && message.endsWith("]")) {
                        message = JsonFormat.formatJson(JsonFormat.decodeUnicode(message));
                    }
                    mMessage.append(message.concat("\n"));
                    // 响应结束，打印整条日志
                    if (message.startsWith("<-- END HTTP")) {
                        LogUtil.showLog(TAG,mMessage.toString());
                    }
                }

            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            getGlobalOkHttpBuilder().addNetworkInterceptor(loggingInterceptor);
        }
        return this;
    }


    /**
     * 设置读取超时时间
     * @param second
     * @return
     */
    public HttpAllocator setReadTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时时间
     * @param second
     * @return
     */
    public HttpAllocator setWriteTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置连接超时时间
     * @param second
     * @return
     */
    public HttpAllocator setConnectTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    private Retrofit.Builder getGlobalRetrofitBuilder() {
        return RetrofitClient.getInstance().getRetrofitBuilder();
    }

    private OkHttpClient.Builder getGlobalOkHttpBuilder() {
        return RetrofitClient.getInstance().getOkHttpBuilder();
    }

    /**
     * 构建Retrofit
     * @return
     */
    public static Retrofit buildGlobalRetrofit() {
        return RetrofitClient.getInstance().buildRetrofit();
    }

    /**
     * 创建api
     * @param kClass 需要创建的api
     * @param <K>
     * @return
     */
    public static <K> K createApi(final Class<K> kClass){
        return buildGlobalRetrofit().create(kClass);
    }
}
