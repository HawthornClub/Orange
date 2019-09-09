package com.zhidiantech.orangeframe.util;

import android.content.Context;
import android.widget.Toast;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2018/12/21 下午3:08
 * Changes (from 2018/12/21)
 *
 * Toast工具类
 * -----------------------------------------------------------------
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * 单例Toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 单例Toast
     * @param context
     * @param msg
     * @param length 弹出时长
     */
    public static void showToast(Context context, String msg, int length) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, length);
        } else {
            mToast.setText(msg);
            mToast.setDuration(length);
        }
        mToast.show();
    }

}
