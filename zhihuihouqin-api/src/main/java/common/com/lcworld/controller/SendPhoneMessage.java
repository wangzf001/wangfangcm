package com.lcworld.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lcworld.cloopen.CCPRestSmsSDK;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;

public class SendPhoneMessage {

	
	public R sendMessage(String mobile){
		HashMap<String, Object> result = null;

		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
//		restAPI.init("app.cloopen.com", "8883");
		restAPI.init("sandboxapp.cloopen.com", "8883");
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount("8aaf0708591b6f1c01591f9cc32102bb", "c3b76516852c432d98fb130867149556");
		
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*******************************************************************************
		restAPI.setAppId("8aaf0708591b6f1c01591f9cc4b302c0");
		
		//*调用发送模板短信的接口发送短信                                                                  *
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		
		String captcha = WebUtils.getCapacha();
		//result = restAPI.sendTemplateSMS(mobile,"1" ,new String[]{captcha,"2"});
		result = restAPI.sendTemplateSMS("18637328744","1" ,new String[]{captcha,"2"});
		
		int statusCode = (int) result.get("statusCode");
		String statusMsg =  (String) result.get("statusMsg");
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			data.get("");
			
			/*Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}*/
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		
		return R.error(statusCode, statusMsg);
	}
}
