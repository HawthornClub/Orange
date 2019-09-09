package com.zhidiantech.orangesample.frametest.api;

import com.zhidiantech.orangesample.BuildConfig;
import com.zhidiantech.orangesample.frametest.api.base.BaseResponse;
import com.zhidiantech.orangesample.frametest.api.sampleapi.SampleResponse;
import com.zhidiantech.orangesample.frametest.api.userapi.FollowResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/4 下午1:54
 * Changes (from 2019/1/4)
 * <p>API管理方案
 * -----------------------------------------------------------------
 */
public interface OrangeSampleApiManager {
    /*
       URL地址管理
     */
    String API_BASE_URL=BuildConfig.API_HOST;
    String H5_BASE_URL=BuildConfig.H5_HOST;

    String API_BASE_URL_V2=BuildConfig.API_HOST+"v2";
    String H5_BASE_URL_V2=BuildConfig.H5_HOST+"v2";

    /*
      接口管理
     */

    /**
     * User相关的所有接口
     */
    interface UserApi {
        /**
         * 关注他人
         * @param userId 他人id
         * VERSION 1.0.0
         */
        @POST("users/add_follow")
        Observable<BaseResponse<FollowResponse>> addFollow(@Query("userId") String userId);
    }

    /**
     * Order订单相关接口
     */
    interface OrderApi {
        /**
         * 获取所有订单
         * VERSION 2.2.0
         */
        @GET("all_order")
        Observable<BaseResponse<List<BaseResponse>>> getAllOrder();
    }

    /**
     * User相关的所有接口
     */
    interface SampleApi {
        /**
         * 关注他人
         * @param userId 他人id
         * VERSION 1.0.0
         */
        @POST("sample")
        Observable<BaseResponse<SampleResponse>> getSample(@Query("userId") String userId);
    }
}
