package com.lcworld.utils;

import java.util.Map; 

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushPayloadUtil {
	
	public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }
	
	
	public static PushPayload buildPushObject_all_alias_alert(String alias,String alert,Map<String, String> map, boolean aPNS_PRODUCTION) {
		Builder b = PushPayload.newBuilder();
        b.setPlatform(Platform.all());
        b.setAudience(Audience.alias(alias));
        b.setOptions(Options.newBuilder().setApnsProduction(aPNS_PRODUCTION).build());

        cn.jpush.api.push.model.notification.Notification.Builder builder = Notification.newBuilder();
        
    	cn.jpush.api.push.model.notification.IosNotification.Builder iosBuilder = IosNotification.newBuilder();
    	iosBuilder.setAlert(alert);
    	iosBuilder.addExtras(map);
    	builder.addPlatformNotification(iosBuilder.build());

    	cn.jpush.api.push.model.notification.AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder();
        androidBuilder.setAlert(alert);
        androidBuilder.addExtras(map);
        builder.addPlatformNotification(androidBuilder.build());
        
        b.setNotification(builder.build());
        //b.setMessage(Message.content("hehe"));
        // 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的
        //b.setOptions(Options.newBuilder().setApnsProduction(true).build());
        /*if(ValidateUtil.isValid(title)){
        	b.setNotification(Notification.android(alert, title, null));
        }else{
            b.setNotification(Notification.alert(alert));
        }*/
        return b.build();
    }
}
