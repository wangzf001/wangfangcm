package com.lcworld.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 短信服务工具类
 * @author Administrator
 *
 */
public class SmsUtils {
    private static  SmsUtils utils = new SmsUtils();
    private Logger log = LoggerFactory.getLogger(SmsUtils.class);
    private static String ACCOUNT = "C57672151";
    private static String PASSWORD = "97305af00053144712319387550bea7f";
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    
    public static  SmsUtils getInstance(){
       return utils;
    }
    
    public void sendinfo(String mobile,String content){
        HttpClient client = new HttpClient(); 
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", ACCOUNT), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", PASSWORD),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", mobile), 
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            if("2".equals(code)){
                 log.info("发送成功");
            }else{
                log.info("发送失败，code："+code+"msg:"+msg+"smsid"+smsid);
            }
        } catch (Exception e) {
           log.error("发送失败");
        } 
    }
    public void send(String mobile,String captcha){
        String content = new String("您好，您的验证码是："+captcha+"（验证码有效期5分钟）如非本人操作，请忽略本短信。");
        sendinfo(mobile,content);
    }
    
    public static void main(String[] args) {
        SmsUtils.getInstance().send("15110151224", "1234");
    }
   
}
