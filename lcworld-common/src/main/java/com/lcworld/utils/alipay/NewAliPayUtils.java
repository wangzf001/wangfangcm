package com.lcworld.utils.alipay;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

public class NewAliPayUtils {
    static AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.partner, AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.ali_public_key, "RSA2");
    
    /**支付
     * @param payvo
     * @return
     */
    public static String pay(AliPayPayVo payvo){
      //实例化客户端
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(payvo.getBody());
        model.setSubject(payvo.getSubject());
        model.setOutTradeNo(payvo.getOutTradeNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(payvo.getTotalAmount());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(payvo.getNotifyUrl());
        try {
                //这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
                return response.getBody();
            } catch (AlipayApiException e) {
                e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param vo
     * @throws AlipayApiException
     */
    public static PayRefundVo  queryRefund(PayRefundVo vo) throws AlipayApiException{
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(JSONObject.toJSONString(vo));
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        JSONObject refund = JSONObject.parseObject(response.getBody()).getJSONObject("alipay_trade_fastpay_refund_query_response");
        PayRefundVo rvo= new PayRefundVo();
        rvo.setOut_request_no(refund.getString("out_request_no"));
        rvo.setRefund_amount(refund.getString("refund_amount"));
        rvo.setTotal_amount(refund.getString("total_amount"));
        rvo.setTrade_no(refund.getString("trade_no"));
        rvo.setOut_trade_no(refund.getString("out_trade_no"));
        return rvo;
    }
    
    /**退款申请
     * @param vo
     * @return
     * @throws AlipayApiException
     */
    public static PayRefundVo   refund(PayRefundVo vo) throws AlipayApiException{
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(JSONObject.toJSONString(vo));
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            vo.setRefund_status("SCUESS");
        } else {
            vo.setRefund_status("FAIL");
            vo.setErrmsg(response.getSubMsg());
        }
        return vo;
    }
    
    public static boolean checkSign( Map<String,String> params) {
        try {
            return AlipaySignature.rsaCheckV1(params, AlipayConfig.ali_public_key, AlipayConfig.input_charset,"RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    
  
    public static void main(String[] args) throws AlipayApiException {
        
        PayRefundVo vo = new PayRefundVo();
        vo.setOut_trade_no("2017102621001004470221629654");
        vo.setTrade_no("2017102621001004470221629654");
        
        vo.setOut_request_no("10091509009871714");
        vo.setTotal_amount("0.01");
        vo.setRefund_amount("0.01");
        System.out.println(JSONObject.toJSON(refund(vo)));
    }
    
    
    
  
}
