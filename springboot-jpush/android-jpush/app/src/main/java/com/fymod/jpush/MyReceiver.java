package com.fymod.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {

	private static final String TAG = "fymod-jpush";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Log.d(TAG, "收到了RegistrationId，一般用户连同其他信息发送到服务器中。RegistrationId为: " + regId);

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				// 此种（通知栏无效果情况），我们使用主页面查看效果。如果在前端运行，那么就给显示到TextView上
				Log.d(TAG, "收到推送的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				// 通知是有效果的，只知道有通知发送过来了，并没有太大实际用途
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Log.d(TAG, "收到推送下来的通知的ID: " + notifactionId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				// 用户点击了同组织，此种情况下是很有用的，可以根据通知不同内容调整到不同的页面
				// 比如bundle.getString(JPushInterface.EXTRA_EXTRA)可以获取附件信息数据
				Log.d(TAG, "用户点击打开了通知");
				Intent i = new Intent(context, MainActivity.class);
				i.putExtras(bundle);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
				Log.d(TAG, "用户收到到富媒体消息，携带参数" + bundle.getString(JPushInterface.EXTRA_EXTRA));

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				// 网络状态变更
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);

			} else {
				Log.d(TAG, "其他未分析的类型在这里 " + intent.getAction());
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		// 如果MainActivity在前端运行，就把正在显示的TextView赋值上
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainApplication.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainApplication.KEY_MESSAGE, message);
			if (extras != null) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (extraJson.length() > 0) {
						msgIntent.putExtra(MainApplication.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}
			}
			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}
	}
}
