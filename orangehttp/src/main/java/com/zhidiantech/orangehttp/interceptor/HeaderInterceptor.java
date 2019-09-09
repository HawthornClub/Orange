package com.zhidiantech.orangehttp.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/14 下午2:17
 * Changes (from 2019/1/14)
 * <p>基本请求头拦截器
 * -----------------------------------------------------------------
 */
public class HeaderInterceptor implements Interceptor {

    private Map<String, Object> mHeaderMap = new HashMap<>();

    private List<String> mHeaderNameList = new ArrayList<>();

    private boolean mIsRemove = false;

    public HeaderInterceptor() {
    }

    /**
     * 构造器
     *
     * @param headers 请求头k-v
     */
    public HeaderInterceptor(Map<String, Object> headers) {
        this.mHeaderMap = headers;
    }

    /**
     * 移除指定的一组请求头
     *
     * @param headerNames 移除的请求头Name组
     */
    public void removeHeaderName(List<String> headerNames) {
        mHeaderNameList = headerNames;
        mIsRemove = true;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuild = chain.request().newBuilder();
        if (mIsRemove) {
            for (String s : mHeaderNameList) {
                requestBuild.removeHeader(s).build();
            }
        } else {
            if (mHeaderMap != null && mHeaderMap.size() > 0) {
                for (Map.Entry<String, Object> entry : mHeaderMap.entrySet()) {
                    requestBuild.addHeader(entry.getKey(), (String) entry.getValue());
                }
            }
        }
        return chain.proceed(requestBuild.build());
    }
}
