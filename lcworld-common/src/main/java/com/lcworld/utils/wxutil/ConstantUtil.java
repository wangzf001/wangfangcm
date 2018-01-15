package com.lcworld.utils.wxutil;

public class ConstantUtil {
	/**
	 * 商家可以考虑读取配置文件
	 */
	

    // 初始化 
    // 微信开发平台应用id
    //public static String APP_ID = "wx3ab6a7e2b95e7d08";
    //public static String APP_ID = "wx4ec0b35473fa09da";
    public static String APP_ID = "wx723f038c9add02a3";
    // 应用对应的密钥
    //public static String APP_SECRET = "d07c97cb85b9386e49e1159551fbb178";
   // public static String APP_SECRET = "fdbe92e06e843b0d597d0229e04c0fad";
    public static String APP_SECRET = "30bdb5d1d098595e53b5593030fb9eea";
   
    // 财付通商户号
    //public static String PARTNER = "1366383602";
    //public static String PARTNER = "1467892602";
    public static String PARTNER = "1490073492";
    // 应用对应的凭证
   // public static String APP_KEY = "e31aed1c7689b485ea921999e6362ad3";
    //public static String APP_KEY = "junhejuntaicimingyisheng20170517";
    public static String APP_KEY = "e31aed1c7689b485ea921999e6362a3e";
    // 商户号对应的密钥，自设API秘钥
   // public static String PARTNER_KEY = "e31aed1c7689b485ea921999e6362ad3";
    //public static String PARTNER_KEY = "junhejuntaicimingyisheng20170517";
    public static String PARTNER_KEY = "e31aed1c7689b485ea921999e6362a3e";
    

    public static String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access_token对应的url
    public static String GRANT_TYPE = "client_credential";// 常量固定值
    public static String EXPIRE_ERRCODE = "42001";// access_token失效后请求返回的errcode
    public static String FAIL_ERRCODE = "40001";// 重复获取导致上一次获取的access_token失效,返回错误码
    public static String GATEURL = "https://api.weixin.qq.com/pay/genprepay?access_token=";// 获取预支付id的接口url
    public static String ACCESS_TOKEN = "access_token";// access_token常量值
    public static String ERRORCODE = "errcode";// 用来判断access_token是否失效的值
    public static String SIGN_METHOD = "sha1";// 签名算法常量值

    public static String ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 签名算法常量值
    // package常量值
    public static String packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
    public static String traceid = "testtraceid001";// 测试用户id

}
