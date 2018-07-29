package com.fymod.jpush;

import android.app.Application;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JAnalyticsInterface.setDebugMode(true); //开启调试模式
        JAnalyticsInterface.init(this); //初始化

        JAnalyticsInterface.initCrashHandler(this); //开启crashlog日志上报
    }
}
