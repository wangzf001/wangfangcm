package com.lcworld.utils.alipay;
//
//
//
//
///* *
// *类名：AlipayConfig
// *功能：基础配置类
// *详细：设置帐户有关信息及返回路径
// *版本：3.3
// *日期：2012-08-10
// *说明：
// *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
// *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
//	
// *提示：如何获取安全校验码和合作身份者ID
// *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
// *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
// *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
//
// *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
// *解决方法：
// *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
// *2、更换浏览器或电脑，重新登录查询。
// */
//
//public class AlipayConfig {
//	
//	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//	
//	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//	// public static String partner = "2088121362467301";
//	// 收款支付宝账号
//	// public static String seller_email = "kangyeyayi@sina.com";
//	// 商户的私钥
//	// public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANGDYh8+zPb0y+puR9yXLgGWNvxmMgv3uwxPXGIt8QX8EPWxjvswhlL+eHqyab22B0Oq0et+VaWBoiZtn74Lkosbigk4tjsTT7PmO5bVXn0vVwsOsjxbJMdUug5jQQBRTGNrwynNRTG+PWOlcmjyBY95aJb8Z2OsvJfburQLONijAgMBAAECgYAHD9eh1/sOo+evqjHv5ISfdxG5ZjRvpNUAMDib8pJ6MincLjT0kIkmiP9Em3jm9Ri/liVnO6XlYz4072MAA+HXkOKXAbG1hqhM12heDb+SyRDpVwi/BfSPUlXT5wn3rQV6pwScfy0ibrkFqhaevoGMSeo1RWZgXzMbypvs+HCouQJBAO5rTm9y3VhC97skQnyfb4uPARZOu3WyKe4kSlJ8KlR39Qra3pGXq0NR9gm1b/2ttE06eRFBUfA7KUrOZecVPpUCQQDg9mmhbhqWkI88rqC0V54yIP68O2YpD0PyNvDQdxtoICUUKG/g+qMfFRqAs/PXOJb505Xo3yvcUc1lf01JnURXAkBGm8754Km+5u5ZJQ7vu4Qcb7ihs6V+4MncNjEwpocexvvyQAmkUqes40wbafmFxnjcWlHak17B57N97Qxu2HLVAkB3EfXW+/AWwW0PMV7eFYImBlDOUKJCGVJdYbcUn/ZhpqH5iW2uJvsv+HuOo7DsuYcvtE4kc/tDIgkVL8w4qoUBAkBVOuUEHV4M7IOivN8a4zut5eOizdRHqYLq3uHyD1Wp8jWP3o8CDh9AOsi7Tm0iiESmq0MzRfK2wkwrnJQA9/gG";
//	// 支付宝的公钥，无需修改该值
//	// public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//	
//	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
////	public static String partner = "2088221574978838";
////	// 收款支付宝账号
////	public static String seller_email = "3345262616@qq.com";
////	// 商户的私钥，pkcs8格式
////	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMTjZUUSX6jU+BJePBhSn/CgStBMM3rQS+b0seaXN5NgR2GLMVWBfP6tdOChcjeV/MW6bijpXaSZ5cbRBSQpbQEcJB1uHnxSo9D2zgXgIguAaj9mF+Tfbfu0smbLqPglH53DP3l6MQUCFy37t2/eLeZocmtF9tn7qgyrFEcDqcQ/AgMBAAECgYAafDxzPjWAEKYOUn7jBrpdNDLcMmP6cWrEdsY3rr/bI2h2xrKsPe8Ck3LVhOJId/S45ItNFZdzPQCb7iIy6x+uebfMLQO11m1AH4OKlVl3jR+GyT93p+i+GkhVKmj7RrjTmIax33g9G2TqZlo+8EmFHzQHsSsKDQXK4OqepbzugQJBAPyk54HmC3XcT7hMaJCuO83htD5NLCQW/7D1JADvyuBJeFKWiU51rN3k/YcqsLgBHS0V8wtnuBk3KZhSXJ4q9b0CQQDHgOXewAYsI06yFnYGZBrF12np0TMssWilPJR7yyGpiaaP9F4atlU/jJ60h/JqOhpJCKPASZRoub4snbBPUourAkEA3SQm9H/9ZhQKv8I6APJRrLu0fpgkXA65jxZ0hlFe3oXQyVG1LDU6tBvdgzPt7ZIkDLPaww/tiDzNruPg6B2obQJBAMdovVsSkJjZibVT4hCtSl1p3UGKix4nDbPrg3bhKPpuzf+hf9YvXbIxegh0wgcPoGpTqmluolqzHbU2wJ3oohUCQEknQjczlFEtKIAizLorsh7udN/yuzzKt3bLBP5UwbW487Y1hAUvHGAaEzaRbnKE/it8kPhbMI/zkYs7/xKCy+c=";
////	// 支付宝的公钥，无需修改该值
////	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
////		
////	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
////	
////
////	// 调试用，创建TXT日志文件夹路径
////	public static String log_path = "D:\\";
////
////	// 字符编码格式 目前支持 gbk 或 utf-8
////	public static String input_charset = "utf-8";
////	
////	// 签名方式 不需修改
////	public static String sign_type = "RSA";
//	
////    public static String partner = "2088521454378519";
////    // 收款支付宝账号
////    // 商户的私钥，pkcs8格式    
////    public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALZd3shiMSHtQsOr62Yp/r4VyxecidHB40rd+eumDl3cEbqhMZ5vQ4+nos+CcTPz06x6sBopOeVGWUPfhjYFPFlbJpcj8p5r8KyPa3T+qhngzHl6ADKO9I3raqnCU0VDCnL1wzNpKpGJNvvSpf1rzqP7EItRbBn51kkJnD5kAuWRAgMBAAECgYA0d4dL8WwfTaqCryiXiQeAWrbGgUPpdDXXaUuo72p8uH7lSdEDuxEd+GXP+8Bj6QeCPP4DWM8OVpICBAIR8qmss0RQPmXloziksy7Yzy069YAiwDGfV0TSxKxm25wvjm5d9HrHlL/Pu3Ykg5LDclvfM3vNKsiPHfJtcnFOzWa7vQJBANtG3xJCoUTPobQBQ2L5qGaGvOJUzrJO5Mtv+1yAyvTl1oRyNMSnN27XA+Gt6cLwKxyI/evoq+H231GWMX38kucCQQDU6ImOoy9cL3LxaowBaOqM0VXH2ar3u0ntfxtlag1vH5GVN+csgvcbcv2bXENvvriHRKXsOyjQo4gexDJ4iyzHAkAhgVzIj4HQ3+EXfjHrx7YhpGjsNIU43bE8HKpN4igg75Wtuz2w5tQjjZs8OrTDYqtTuuMBzxWke3J4Jvj9BUD1AkEAlCmK6JdJMwbWH4i9C5Ief4aCAoTIhINya/Mjdoy58pXucwAnQQOWwSRhtkcbqtz3fzLXoLB+s8rJxFdWpCp+KwJBANkgmlGEEKn4ocYGWEWlMi2FBMNunrQZ+MIpAy5+fHrxTozottKBvDVxJ+tCldQZ2w8xYY+cSBbKRrzT4SEIaCo=";
//////  public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ0Prj0h60Bxl1V2RbMThwyQCXRMHN7D432oU93x6ragUYJs9H+GrJXrWzAS8bvDK1MqCgvSzbk6RwIVfoAtqr7vsdCQIUzb/i42YKdXtPW0GeTwzU0f6eK5hE+5kyVCch/q1cdGC6MVUuA+wTCeYAb2/RHME4ZuOylfjqF/tMglAgMBAAECgYBucG9Bl083wmf6dS7vi+SjhcjQGREX50BYxHssjoZPoo2OG5UU8xr6VEOLm1aVg9g8I0xrIfD8tSjnIzwPX402mU+mqJiAMm9K0SxGBRkFwtOWckuLz8WxyzuoV5zeMb27e2N4npuS6CiYyYwh5nILR+dFJQp35fT6gsGaS0rGBQJBANEoBHh8Fa9re8Zd6awr0rQMfcfp/+5VTKzqrzmRrkRr6Vesh07VQNFMWWP8mU4xZgXGRPrWSEc4bwqLaZeR2XMCQQDAPMqTDZlI5XCzSgYpzKkvzvScvdNa+v+gRuEPnKOdIBw51AzAfLWx8ulkTF5eiBGeWBvWl03N95ZMCOVAa1IHAkEAkCgWvLw5bgiU7On5jnM97F7Xqpw7i5c7G8s9Ll6cUAhYcdc2Az20SQ1oZyPIvj4J5Vz8Wi6q7nuU/R2Nj6w0xQJBAJVfp1jUvEL0Zboqztcz/FL10QTqS4zqdC8qZD8pS8kP5l7Y8n+vZ8+6HLiezsVNncMYW6lHMGHW/KZgUyLXQYkCQAVSxeL3lN/kEqzkBWjY/dBNnC3H8BrrveHwpK5bYoAVQPZQkKxg2oDJ7rYY0QMupeBru8Qz12NME2KMQWEQVdI=";  // 支付宝的公钥，无需修改该值
//////                                          MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Xd7IYjEh7ULDq+tmKf6+FcsXnInRweNK3fnrpg5d3BG6oTGeb0OPp6LPgnEz89OserAaKTnlRllD34Y2BTxZWyaXI/Kea/Csj2t0/qoZ4Mx5egAyjvSN62qpwlNFQwpy9cMzaSqRiTb70qX9a86j+xCLUWwZ+dZJCZw+ZALlkQIDAQAB
////    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Xd7IYjEh7ULDq+tmKf6+FcsXnInRweNK3fnrpg5d3BG6oTGeb0OPp6LPgnEz89OserAaKTnlRllD34Y2BTxZWyaXI/Kea/Csj2t0/qoZ4Mx5egAyjvSN62qpwlNFQwpy9cMzaSqRiTb70qX9a86j+xCLUWwZ+dZJCZw+ZALlkQIDAQAB "; //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
////    
//
//    public static String seller_email = "woo6609@163.com";
//    // 调试用，创建TXT日志文件夹路径
//    public static String log_path = "D:\\";
//
//    // 字符编码格式 目前支持 gbk 或 utf-8
//    public static String input_charset = "utf-8";
//    
//    // 签名方式 不需修改
//    public static String sign_type = "RSA";
//    
//
//    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
//    public static String partner = "2088421252677688";
//
//    // 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
//    public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANo3KaJqtl5kVCvA9caklO2cOegsMrVGId/u9rIlFoAfYqGJMOV6+Oyp5sFNYQEpE4mk0tjI6Bp4EYABxH6oWioHWZ7JtkTyk6HrOcsjl+dE2m95x4unrBiWFnT5GztdayOUK3k5CuVEXGYrpHeCDsqkDRJMt9EqFs5Si0J9w+trAgMBAAECgYEAllZQwcvhLrDSaH59FUjILddm8bSZjtfteJjqrxrsIIL+8alR3ZfWLT50cxJ7VVBWXIsIlD/94FZ+QAF3vYcA7hOZBO+J/hQl6WNVkcZ3PtEOD5Gkv3v9P2zfrNTkZyJjfogAwwE1SbcfyjNXWm33h2XLh+I/NsNN5QSFWFzlqaECQQD5HOd8tVkY00JsLJIy2keyARzteG8OQdHbZHzu2onuhepbhEQGAL3RqLApB2lzLI3bVBqOSWKe8I9kXLK4iA3xAkEA4D+U+lq6egQP/2xbP6Aau5IQ1JNHnjafGV1Eu6rFOlULhmnmR90IpC+J+L03Nr+wW1KrEN7h1OTBz6iw7SqjGwJAfNMN74BTV8wsAVZIH7EescQ7AYcSZtt2U2/D5k+dQ+bYaukYyMVAWDklpDlxjmOp5Vf8QoXlI+04fLqyVyLdkQJAKlcW8+Ypi1w1AQ8ec3bxVTURBHICdbbud0WK8rwdnRPtpyVbm3n6ZbWsIVDomee7jqKfNgI/NYkzpte9ACqqvQI/IlS+RpjPeATe+PzohjTNgPDSJAh+Ohf215fSi9z2SLhbGveslOgrs7qjH+xVsTjOfhXl+1CPLL9omDkVB+47";
//
//    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
//    public static String seller_id = partner;
//
//    // 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
//    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//
//    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    // public static String notify_url = "http://商户网址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/notify_url.jsp";
//
//    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://商户网址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/return_url.jsp";
//
//    // 支付类型 ，无需修改
//    public static String payment_type = "1";
//
//    // 调用的接口名，无需修改
//    public static String service = "alipay.wap.create.direct.pay.by.user";
//
//    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//	
//	
//
//}





/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
    
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
    

 //   public static String partner = "2017030105983566";
    public static String partner = "2017101909382403";
    // 收款支付宝账号
    public static String seller_email = partner;
 
//	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCLlzM6/gYdbuz72BLSxMwhDfhyfsUH56V6wCIDJccoojDz1BDuppw0rRdctKoJSjohgHLbE0IaAxa/r4P5dMPBsVCucTYpLd/POajDiAsuG5hiKDa+PdyeyoD3ySt22uLiZVz7pA4srb+5QXt5vGNHMDQmbcpOmtH1p98+kVIeKKsiLX52b9A29GrcB2cyZL4mHioHLq+f/0+Nzl1SOi1DRTO7twNZNUzF0JlcBNP43JI3/HiS15l5CRnJwr2Gs/COp5p/+FiCDSvYhiGoBR1wjz3D3EM0lR9raDOODpHWdBVjAXfIFZzDNj5eHL7kMXII8Xv29WXNes+bfffrqHdhAgMBAAECggEAFua3gPYsCqgoOSk57VpcIxAnsqEZsjJO89SRv/Jyujfh0B76qCytc/bx3kPFocCLyPNJ5KdEMQBQuyIQhNyjQSPWGMh6GkdscJMFHQ80xlvApFn0oUGE8IhIoF5gwx8mGf6HQsGnkazvx3T2zQIbR5Kg+60Hg93ilGr2NU2iK1BCdbpS8fnWWmvVrkmlPh8VUBFNLy9ypAj9vSxUm4Md0OkSjnNKj3DFx3k/lrrdRqfBgCCLXLwxGulbihBk4wyDfljbHetUYVw06pLp0xPIKqzfmJyjI+N0x+tQOwO9wPP3SmgSibBWDo9hrcIcwHuWYkLbHrzAQkGno6iw7qhIAQKBgQDPtHmMjhEmk7qqgMOPtJmaeF/uZ4np9i3RiFVqh62He7O0dFXvDgyaNAFEmbUJhOameXWByCKJeM14dPuou3iOEFpKG6BqWzr3ZWk0tAeh+XNauN8Hy4UH/4M8/g7dleh/C5hcaCasR+U9LYAHckeWIJSZEW44QtScKwWJp3KrcwKBgQCsDEFz5oqnoaZSBS+RNhJLsutALvZAoMeLlsGq/EUv+CqOuAB72PwAd+2jnd9QgxQz8ACYYWJyf5pVcK8bMCaoVHvyIqqKzUiIGQWVMP/hmOTJAe+ecgx03Wp7vsLshXTqMJGPhfXNddqF1FTY6DDvAaqptz7SIvwPgNfwwhYE2wKBgQCjiBW3K1enhuKOQck3SNszOplVLCHymgfjFDMkJ+ThaezIJFxQVkBn3JAvAdH+x6uN4M06vGcs03b64ygM5mV5fLiJ5ox7kI6xoB+gBiqIRc2j3TCEg6Ge9XbPu7vajJ+7/1YbU0k3lXeQnlVSB5EDS22TwpyEIWIh/uFiSvtGrwKBgQCMuKmAhvy6B7nmNF+nwjZMvFc29DJuwhL01aYqmJRum+kTOuUKZ3wXVgCFZ0eUruIDaTbTbDP9uukdIUHYVxYHcLLAZiJfzXCaAP1T/fQNLeVTk8XOLzKJE+FYvbrpgm8Sbg9g+Q0HtHG8LOQJVkFP/Gj+z+cOMZdBhv0Y62bfKQKBgD9PAhYF9dJYftD4nfSdJqj3dZ2s+OPU9r5FiDGtdtINLAFN9JlhbvTmuZcQzGe4zVwUD1pfXhJL3/GGlxCAu/1oE+uOSCCcqDS2dAyZ9oNYGXq+xItplFqCyiGYir2sWQbQhpYMsikxuxK+MBQkg1EaTD2PYNihJkTsX3VDDQcY";
	public static String private_key = "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";
	//public static String ali_public_key  ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiYK+V7ZrBXduw+Dhz9uXa992XtOMZL85B+Vgf6vqdOPWavcsycqvB52LmlX605Y2ixbCLJfzE5B5+V49UNDAlmeM5F5Ti+MzIJZ8SmMgEI5VTN2hLM1+trV1BpL42CQUy5svDVbb2CfjO1eydxm2Z9UovzckS7CnTcKrG8xJMZSexnVcGS3NkbEcrGeloZieCsYdBg20dCwoZvT24rG4oOn4s8qKVJVfnHLJvhSjVEekd4UHkx3Is50gYSZ0NFBCmfMnA/kV02uHvvhaWlKhvmug/C6zKZJQM6YCP9VCT+lMObWTEnUlMjRkiVgxr6mU+Z0ozJ6eP9B/2p+1so4gQQIDAQAB"; 
	public static String ali_public_key  ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmFW4pUM9U3ERRsgV1f8p2PRAL5Y/E3IbMih34AEWIE3NosVwLHhSBVFVnbbMVAtG0+MQE8Q0on8OOJ+xDeSGlLveTc773nvAVXZLR8dh2FjEt75lXTbRur5xW9zYl9yidPWzcUQBzxBKoRyV/Fo0+XyCFyIqRjMZaMaKWmMnhQPwmPeb9GsMX3ymXqfEsLxoC3azOldqy1GPpFH2NalAlo0U8TVGdfIWjRjpw8V5tTcxp7WY6+6UyBnx8VmSqV80u2JGbZemtDmTB5YoQ5EkvD6wAmft9l9IzwUeJQzBLAUAcTWwwdwCbvhi2m1Z70R91OLbqUyLQOAZpRZEvumj4QIDAQAB+V7ZrBXduw+Dhz9uXa992XtOMZL85B+Vgf6vqdOPWavcsycqvB52LmlX605Y2ixbCLJfzE5B5+V49UNDAlmeM5F5Ti+MzIJZ8SmMgEI5VTN2hLM1+trV1BpL42CQUy5svDVbb2CfjO1eydxm2Z9UovzckS7CnTcKrG8xJMZSexnVcGS3NkbEcrGeloZieCsYdBg20dCwoZvT24rG4oOn4s8qKVJVfnHLJvhSjVEekd4UHkx3Is50gYSZ0NFBCmfMnA/kV02uHvvhaWlKhvmug/C6zKZJQM6YCP9VCT+lMObWTEnUlMjRkiVgxr6mU+Z0ozJ6eP9B/2p+1so4gQQIDAQAB"; 
	

    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    
    // 签名方式 不需修改
    public static String sign_type = "RSA2";

}
