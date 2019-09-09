package com.zhidiantech.orangeframe.support.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2019/1/16 下午5:29
 * Changes (from 2019/1/16)
 * -----------------------------------------------------------------
 */
public class EventBusUtils {
    /**
     * 注册 EventBus
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber);
        }
    }

    /**
     * 解除注册 EventBus
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }
    }

    /**
     * 发送事件消息
     *
     * @param event
     */
    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件消息
     *
     * @param event
     */
    public static void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

}
