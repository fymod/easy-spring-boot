package com.fymod.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    // 标识当前的Activity是否在前端运行
    public static boolean isForeground = false;

    @BindView(R.id.txt_message)
    TextView txtMessage; //用来显示文本消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerMessageReceiver(); //注册广播
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @OnClick({R.id.btn_get_registeration_id, R.id.btn_set_tag, R.id.btn_add_tag, R.id.btn_set_alias})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_registeration_id:
                String rid = JPushInterface.getRegistrationID(getApplicationContext());
                if (!rid.isEmpty()) {
                    Toast.makeText(this, rid, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_tag:
                Set<String> tags = new HashSet<>();
                tags.add("woman");
                tags.add("beijing");
                JPushInterface.setTags(this, 0, tags);
                break;
            case R.id.btn_add_tag:
                Set<String> addtags = new HashSet<>();
                addtags.add("swim");
                JPushInterface.addTags(this, 0, addtags);
                break;
            case R.id.btn_set_alias:
                JPushInterface.setAlias(this, 0, "fymod");
                break;
        }
    }

    /**
     * 注册广播
     */
    public void registerMessageReceiver() {
        MessageReceiver mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MainApplication.MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    /**
     * 广播接收器
     */
    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MainApplication.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(MainApplication.KEY_MESSAGE);
                    String extras = intent.getStringExtra(MainApplication.KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(MainApplication.KEY_MESSAGE + " : " + messge + "\n");
                    if (extras != null) {
                        showMsg.append(MainApplication.KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }

    private void setCostomMsg(String msg) {
        txtMessage.setText(msg);
    }


}
