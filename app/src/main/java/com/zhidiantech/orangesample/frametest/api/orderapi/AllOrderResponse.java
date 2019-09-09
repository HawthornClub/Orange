package com.zhidiantech.orangesample.frametest.api.orderapi;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/4 下午3:05
 * Changes (from 2019/1/4)
 * -----------------------------------------------------------------
 */
public class AllOrderResponse {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "AllOrderResponse{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
