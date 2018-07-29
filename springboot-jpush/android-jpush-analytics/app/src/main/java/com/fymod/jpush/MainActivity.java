package com.fymod.jpush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.Currency;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //event:事件模型，支持CountEvent(计数事件)、CalculateEvent(计算事件)、RegisterEvent(注册事件)、LoginEvent(登录事件)、BrowseEvent(浏览事件)、PurchaseEvent(购买事件)
        testCountEvent();
        testCalculateEvent();
        testRegisterEvent();
        testLoginEvent();
        testBrowseEvent();
        testPurchaseEvent();

    }

    // 测试计数事件
    public void testCountEvent() {
        CountEvent cEvent = new CountEvent("eventId");
        cEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, cEvent);
    }

    // 测试计算事件
    // 通过相同的事件不同的值进行累加
    public void testCalculateEvent() {
        CalculateEvent cEvent = new CalculateEvent();
        cEvent.setEventId("eventId2");
        cEvent.setEventValue(999.9).addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, cEvent);
    }

    // 测试注册事件
    public void testRegisterEvent() {
        RegisterEvent rEvent = new RegisterEvent("sina",true);
        rEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, rEvent);
    }

    // 测试登录事件
    public void testLoginEvent() {
        LoginEvent lEvent = new LoginEvent("qq",true);
        lEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, lEvent);
    }

    // 测试浏览事件
    // 上传参数包含浏览了多长时间，单位是秒
    public void testBrowseEvent() {
        BrowseEvent bEvent = new BrowseEvent("browseId","热点新闻","news",30);
        bEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, bEvent);
    }

    // 测试购买事件
    public void testPurchaseEvent() {
        PurchaseEvent pEvent = new PurchaseEvent("goodsId","篮球",300,true, Currency.CNY,"sport",100);
        pEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, pEvent);
    }

    @Override
    protected void onResume() {
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
        super.onPause();
    }

}
