package com.zhidiantech.orangeframe.util;

import android.util.Log;

import com.zhidiantech.orangeframe.BuildConfig;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 sun
 * -----------------------------------------------------------------
 * Create: 2018/12/21 下午3:24
 * Changes (from 2018/12/21)
 *
 * 日志工具类
 * -----------------------------------------------------------------
 */
public class LogUtil {
    //是否开启Log
    public static boolean logEnable = BuildConfig.DEBUG;

    /**
     * 打印log设置级别
     * @param TAG
     * @param msg
     * @param level
     */
    public static void showLog(String TAG, String msg, int level) {
        if (!logEnable) {
            return;
        }
        switch (level) {
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
        }
    }

    /**
     * 打印log
     * @param TAG
     * @param msg
     */
    public static void showLog(String TAG, String msg) {
        if (!logEnable) {
            return;
        }
        Log.d(TAG,msg);

    }

    /**
     * 打印json日志
     * @param TAG
     * @param msg
     * @param level
     */
    public static void showLogJson(String TAG, String msg,int level) {
        if (!logEnable) {
            return;
        }
        String formatStr=formatJson(msg);
        showLog(TAG,formatStr,level);
    }


    /**
     * 格式化json字符串
     * @param jsonStr
     * @return
     */
    private static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {return "";}
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            //遇到{ [换行，且下一行缩进
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                //遇到} ]换行，当前行缩进
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                //遇到,换行
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
