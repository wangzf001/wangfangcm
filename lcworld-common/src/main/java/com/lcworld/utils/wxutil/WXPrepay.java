package com.lcworld.utils.wxutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.utils.alipay.PayRefundVo;
import com.lcworld.utils.tenpay.util.WXUtil;

/**
 * 生成预支付订单号
 * 
 * @author Sunlight
 *
 */
public class WXPrepay {
    private static String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static String orderquery = "https://api.mch.weixin.qq.com/pay/orderquery";
    private static String closeorder = "https://api.mch.weixin.qq.com/pay/closeorder";
    private static String refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    private static String refundquery = "https://api.mch.weixin.qq.com/pay/refundquery";
    private String appid;
    private String mch_id;
    private String nonce_str = WXUtil.getNonceStr();
    private String body;
    private String out_trade_no;
    private String total_fee;
    private String spbill_create_ip;
    private String trade_type;
    private String notify_url;
    private String sign;
    private String partnerKey;
    // 预支付订单号
    private String prepay_id;
    private String transaction_id;
    private String out_refund_no;
    private String refund_fee;
    private String op_user_id = "1296612601";
    // add
    private String attach;
    public String refund_success_time;

    public String getAttach() {
        return attach;
    }

    public String getRefund_success_time() {
        return refund_success_time;
    }

    public void setRefund_success_time(String refund_success_time) {
        this.refund_success_time = refund_success_time;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    private String result_code;

    /**
     * 生成预支付订单
     * 
     * @return
     */
    public String submitXmlGetPrepayId() {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(unifiedorder);
        String xml = getPackage();
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                String prepay_id = Jsoup.parse(result).select("prepay_id").html();
                this.prepay_id = prepay_id;
                if (prepay_id != null)
                    return prepay_id;
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prepay_id;
    }

    /**
     * 关闭订单
     * 
     * @return
     */
    public String closeOrder() {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(closeorder);
        String xml = getCloseOrderPackage();
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                String return_code = Jsoup.parse(result).select("return_code").html();

                return return_code;

            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_code;
    }

    /**
     * 退款
     * 
     * @return
     * @throws Exception
     */
    public HashMap<String,String> refund() throws Exception {
        // 创建HttpClientBuilder
        // HttpClient
    	HashMap<String,String> resultmap = new HashMap<String,String>();
        CloseableHttpClient closeableHttpClient = getSSLHttpClient();
        HttpPost httpPost = new HttpPost(refund);
        String xml = getRefundPackage();
        StringEntity entity;
        String return_code = null;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                return_code = Jsoup.parse(result).select("result_code").html();
                resultmap.put("return_code", return_code);
                String err_code = Jsoup.parse(result).select("err_code").html();
                resultmap.put("err_code", err_code);
                String err_code_des = Jsoup.parse(result).select("err_code_des").html();
                resultmap.put("err_code_des", err_code_des);
                return resultmap;

            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultmap;
    }

    /**
     * 请求订单查询接口
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> reqOrderquery() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(orderquery);
        String xml = getPackage();
        StringEntity entity;
        Map<String, String> map = null;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            // getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                try {
                    map = XMLUtil.doXMLParse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getPackage() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("body", this.body);
        treeMap.put("out_trade_no", this.out_trade_no);
        treeMap.put("total_fee", this.total_fee);
        treeMap.put("spbill_create_ip", this.spbill_create_ip);
        treeMap.put("notify_url", this.notify_url);
        treeMap.put("trade_type", this.trade_type);
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        sb.append("key=" + partnerKey);
        sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        treeMap.put("sign", sign);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue())
                        .append("]]></" + entry.getKey() + ">\n");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            }
        }
        xml.append("</xml>");
        System.out.println("\n\ngetPackage" + xml.toString());
        return xml.toString();
    }

    public String getCloseOrderPackage() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("out_trade_no", this.out_trade_no);
        treeMap.put("trade_type", this.trade_type);
        treeMap.put("body", this.body);

        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        sb.append("key=" + partnerKey);
        sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        treeMap.put("sign", sign);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue())
                        .append("]]></" + entry.getKey() + ">\n");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            }
        }
        xml.append("</xml>");
        System.out.println("\n\ngetCloseOrderPackage" + xml.toString());
        return xml.toString();
    }

    public String getRefundPackage() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("op_user_id", this.op_user_id);
        treeMap.put("out_refund_no", this.out_refund_no);
        treeMap.put("refund_fee", this.refund_fee);
        treeMap.put("total_fee", this.total_fee);
        treeMap.put("transaction_id", this.transaction_id);
        // treeMap.put("trade_type", this.trade_type);
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        sb.append("key=" + partnerKey);
        sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        treeMap.put("sign", sign);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue())
                        .append("]]></" + entry.getKey() + ">\n");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            }
        }
        xml.append("</xml>");
        System.out.println("\n\ngetRefundPackage" + xml.toString());
        return xml.toString();
    }

    public CloseableHttpClient getSSLHttpClient() throws Exception {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // 读取本机存放的PKCS12证书文件 (放入项目中)
        InputStream instream = WXPrepay.class.getClassLoader().getResourceAsStream("apiclient_cert.p12");
        try {
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, "1467892602".toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = new SSLContextBuilder().loadKeyMaterial(keyStore, "1467892602".toCharArray()).build();
        // 指定TLS版本
        @SuppressWarnings("deprecation")
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        // 设置httpclient的SSLSocketFactory
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();

    }

    public PayRefundVo refundquery() {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(refundquery);
        String xml = getRefundQueryPackage();
        StringEntity entity;
        String refund_status;
        PayRefundVo obj = new PayRefundVo();
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                obj = packageRefundVo(result);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**封装返回参数
     * @param result
     * @return
     */
    private PayRefundVo packageRefundVo(String result) {
        Document presult = Jsoup.parse(result);
        PayRefundVo obj = new PayRefundVo();
        obj.setOut_trade_no(presult.select("out_trade_no").html());
        obj.setOut_request_no(presult.select("out_refund_no_0").html());
        obj.setRefund_amount(presult.select("refund_fee_0").html());
        obj.setRefund_status(presult.select("refund_status_0").html());
        obj.setRefund_time(presult.select("refund_success_time_0").html());
        obj.setTotal_amount(presult.select("total_fee").html());
        obj.setTrade_no(presult.select("transaction_id").html());
        return obj;
	    
    }

    private String getRefundQueryPackage() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("out_refund_no", this.out_refund_no);
        treeMap.put("transaction_id", this.transaction_id);
        // treeMap.put("trade_type", this.trade_type);
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        sb.append("key=" + partnerKey);
        sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        treeMap.put("sign", sign);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue())
                        .append("]]></" + entry.getKey() + ">\n");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            }
        }
        xml.append("</xml>");
        System.out.println("\n\ngetRefundPackage" + xml.toString());
        return xml.toString();
    }

    public static void main(String[] args) throws Exception {

        String appid = ConstantUtil.APP_ID;
        String partner = ConstantUtil.PARTNER;
        String partnerkey = ConstantUtil.PARTNER_KEY;
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(appid);
        prePay.setBody("he");
        prePay.setPartnerKey(partnerkey);
        prePay.setMch_id(partner);
        prePay.setOut_trade_no("8296834487159804");
        prePay.setOut_refund_no("8296834487159804");

        prePay.setTransaction_id("4007652001201706074721985254");
        PayRefundVo m = prePay.refundquery();

        System.out.println(JSONObject.toJSON(m));
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

}
