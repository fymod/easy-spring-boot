package com.fymod.jpush;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.utils.StringUtils;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushExample {

	// 极光后台分配的pApKey和Master Secret
	protected static final String APP_KEY = "201dc8c87b057a824b49ac94";
	protected static final String MASTER_SECRET = "f6e564a3065727c24ac3bf21";

	public static void main(String[] args) {
		testSendMessage();
		testSendPush();
	}

	public static void testSendMessage() {
		Map<String, String> extras = new HashMap<>();
		extras.put("k1", "v1");
		extras.put("键2", "值2");
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setMessage(Message.newBuilder().setMsgContent("消息内容").addExtras(extras).build()).build();

		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		} catch (APIConnectionException e) {
			// API连接异常
			System.out.println(payload.getSendno());
		} catch (APIRequestException e) {
			// API请求异常
			e.printStackTrace();
		}
	}

	public static void testSendPush() {

		 PushPayloadParam param = new PushPayloadParam();
		// param.setTitle("安卓端的标题");
		 param.setContent("这是推送显示内容");
		 final PushPayload payload = buildPushPayloadObject(param);

		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		} catch (APIConnectionException e) {
			// API连接异常
			System.out.println(payload.getSendno());
		} catch (APIRequestException e) {
			// API请求异常
			e.printStackTrace();
		}
	}

	/**
	 * 广播
	 */
	public static PushPayload buildAllPushObject(String content) {
		return PushPayload.alertAll(content);
	}

	public static PushPayload buildPushPayloadObject(PushPayloadParam param) {
		// 安卓端的参数
		cn.jpush.api.push.model.notification.AndroidNotification.Builder androidBuilder = AndroidNotification
				.newBuilder();
		if (!StringUtils.isEmpty(param.getTitle())) {
			androidBuilder = androidBuilder.setTitle(param.getTitle());
		}
		if (param.getExtras() != null) {
			androidBuilder = androidBuilder.addExtras(param.getExtras());
		}
		// iOS端的参数，incrBadge是桌面图片上显示的数量加1，需要iOS在打开APP的时候清零
		cn.jpush.api.push.model.notification.IosNotification.Builder iosBuilder = IosNotification.newBuilder()
				.incrBadge(1);
		if (param.getExtras() != null) {
			iosBuilder = iosBuilder.addExtras(param.getExtras());
		}
		// registrationId 标签和别名
		Audience audience = Audience.all();
		if (param.getRegistrationIds() != null && param.getRegistrationIds().size() > 0) {
			audience = Audience.registrationId(param.getRegistrationIds());
		} else if (param.getTags() != null && param.getTags().size() > 0) {
			audience = Audience.tag(param.getTags());
		} else if (param.getAlias() != null && param.getAlias().size() > 0) {
			audience = Audience.alias(param.getAlias());
		}

		return PushPayload.newBuilder().setPlatform(param.getPlatform()).setAudience(audience)
				.setNotification(Notification.newBuilder().setAlert(param.getContent())
						.addPlatformNotification(androidBuilder.build()).addPlatformNotification(iosBuilder.build())
						.build())
				.build();
	}

}

class PushPayloadParam {
	private String title; // 标题，仅安卓端有效
	private String content; // 通知栏显示的内容
	private Map<String, String> extras; // 附加数据，用于业务场景
	private Platform platform = Platform.all(); // 平台，默认全部，可以单独指定某个或某几个，比如指定安卓和iOS
	private Collection<String> registrationIds;
	private Collection<String> alias; // 别名
	private Collection<String> tags; // 标签

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Collection<String> getAlias() {
		return alias;
	}

	public void setAlias(Collection<String> alias) {
		this.alias = alias;
	}

	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}

	public Collection<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(Collection<String> registrationIds) {
		this.registrationIds = registrationIds;
	}
}
