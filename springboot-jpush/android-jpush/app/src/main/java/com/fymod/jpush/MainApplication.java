package com.fymod.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class MainApplication extends Application {

    // 广播消息Action名称
    public static final String MESSAGE_RECEIVED_ACTION = "com.fymod.jpush.MESSAGE_RECEIVED_ACTION";
    // 对应极光消息的文本内容字段
    public static final String KEY_MESSAGE = "message";
    // 对应极光消息的附件信息字段
    public static final String KEY_EXTRAS = "extras";

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); //开启调试模式
        JPushInterface.init(this); //初始化
    }
}
