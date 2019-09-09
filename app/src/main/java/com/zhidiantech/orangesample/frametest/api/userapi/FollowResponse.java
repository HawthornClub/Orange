package com.zhidiantech.orangesample.frametest.api.userapi;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/4 下午2:36
 * Changes (from 2019/1/4)
 * -----------------------------------------------------------------
 */
public class FollowResponse {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "FollowResponse{" +
                "info='" + info + '\'' +
                '}';
    }
}
