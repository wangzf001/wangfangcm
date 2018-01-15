package com.lcworld.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

@Component
public class JPushUtilService {
	private String MASTER_SECRET;
	private String APP_KEY;
	private boolean APNS_PRODUCTION;
	private JPushClient jpushClient;
	
	private String SERVER_MASTER_SECRET;
	private String SERVER_APP_KEY;
	private boolean SERVER_APNS_PRODUCTION;
	private JPushClient serverJpushClient;

	private String className = this.getClass().getName();
	private Logger log = LoggerFactory.getLogger(JPushUtilService.class);

	
	@PostConstruct
	private void init() {
		MASTER_SECRET = JpushReader.getProperty("jpush.user.MASTER_SECRET").trim();
		APP_KEY = JpushReader.getProperty("jpush.user.APP_KEY").trim();
		APNS_PRODUCTION = Boolean.valueOf(JpushReader.getProperty("jpush.user.apns_production").trim()).booleanValue();
		jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		

		SERVER_MASTER_SECRET = JpushReader.getProperty("jpush.server.MASTER_SECRET").trim();
		SERVER_APP_KEY = JpushReader.getProperty("jpush.server.APP_KEY").trim();
		SERVER_APNS_PRODUCTION = Boolean.valueOf(JpushReader.getProperty("jpush.server.apns_production").trim()).booleanValue();
		serverJpushClient = new JPushClient(SERVER_MASTER_SECRET, SERVER_APP_KEY, null, ClientConfig.getInstance());
	}

	/**
	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @param content
	 * @return
	 */
	public PushResult sendAllAlert(String alert, boolean isUser){
		PushResult result = null;
		 try {
			 if(isUser){
				 result = jpushClient.sendPush(PushPayloadUtil.buildPushObject_all_all_alert(alert));
			 }else{
				 result = serverJpushClient.sendPush(PushPayloadUtil.buildPushObject_all_all_alert(alert));
			 }
		} catch (APIConnectionException e) {
			log.error(className+":user:sendAllAlert:"+e.toString());
		} catch (APIRequestException e) {
			log.error(className+":user:sendAllAlert:"+e.toString());
		}
		 return result;
	}

	
	/**
	 * 构建推送对象：所有平台，推送目标是别名为 alias；通知内容为 ALERT；map  自定义参数
	 * @param content
	 * @return
	 */
	private PushResult sendAliasAlert(String alias, Map<String, String> map, String alert, boolean isUser){
		PushResult result = null;
		 try {
			 if(isUser){
				 result = jpushClient.sendPush(PushPayloadUtil.buildPushObject_all_alias_alert(alias,alert,map,APNS_PRODUCTION));
			 }else{
				 result = serverJpushClient.sendPush(PushPayloadUtil.buildPushObject_all_alias_alert(alias,alert,map,SERVER_APNS_PRODUCTION));
			 }
		} catch (APIConnectionException e) {
			log.error(className+":user:sendAliasAlert:"+e.toString());
		} catch (APIRequestException e) {
			log.error(className+":user:sendAliasAlert:"+e.toString());
		}
		 return result;
	}

	public PushResult sendUserAliasAlert(Object userId, String code, String content, Object... args){
		Map<String, String> jpush_map = new HashMap<String, String>();
		jpush_map.put(JPushConst.CUSTOMER_PARAM, code);
		
		return sendAliasAlert(String.format(JPushConst.JPUSH_USER_ALIAS, userId),
				jpush_map,
				String.format(content, args),
				true);
	}
	
	public PushResult sendServerAliasAlert(Object orderType, Object userId, String code, String content, Object... args){
		Map<String, String> jpush_map = new HashMap<String, String>();
		jpush_map.put(JPushConst.CUSTOMER_PARAM, code);
		
		return sendAliasAlert(String.format(JPushConst.JPUSH_SERVER_ALIAS, orderType, userId),
				jpush_map,
				String.format(content, args),
				false);
	}
	
	public static void main(String[] args) {
		JPushUtilService s = new JPushUtilService();
		s.sendUserAliasAlert(1, "1000001", "test");
	}

}
