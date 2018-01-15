package com.lcworld.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.service.IOrderService;
import com.lcworld.service.LffwVoucherService;
import com.lcworld.service.PayinfoService;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.RefundinfoService;
import com.lcworld.service.UserWalletService;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.alipay.NewAliPayUtils;
import com.lcworld.utils.tenpay.ResponseHandler;
import com.lcworld.utils.tenpay.util.newwx.WXPayUtil;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.utils.wxutil.ConstantUtil;
import com.lcworld.utils.wxutil.XMLUtil;
import com.lcworld.vo.PayOrderVo;

@RestController
@RequestMapping("appuser/pay")
public class PayController {
    private Logger log = LoggerFactory.getLogger(PayController.class);
    @Autowired
    private OrderServiceFactory orderServiceFactory;
    @Autowired
    private PayinfoService payinfoService;
    @Autowired
    private RefundinfoService refundinfoService;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private PurchaseAccountService purchaseAccountService;
    @Autowired
    private BaseUserRoleService baseUserRoleService;
    @Autowired
    private LffwVoucherService lffwVoucherService;
    
    /**理发首页（店铺）
     * @param biz={orderid,ordercode,ordertype,paytype,paypass,vouchercount}
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public R pay(HttpServletRequest req,String biz){
       R result = new R();
       
       // 解析支付参数
       PayOrderVo pay = FastJSONUtils.getObject(biz, PayOrderVo.class);
       pay.setCurUid(WebUtils.getUid(req));
       
       // 获取service
       IOrderService orderservice = orderServiceFactory.getService(pay.getOrdertype());
       PayOrderVo order;
       
        try {
        	// 获取支付订单基本信息
            order = orderservice.getOrderVo(pay);
            order.setPaytype(pay.getPaytype());
            order.setCurUid(pay.getCurUid());
            order.setOtherpay(pay.getOtherpay());
            if(pay.getVouchercount() != null){
                order.setVouchercount(pay.getVouchercount());
            }
            order.setPaypass(pay.getPaypass());
            JSONObject res = null;
            
            // 1: 是混合支付，0：否
            if(pay.getMixpay() == null){
                order.setMixpay(0);
                pay.setMixpay(0);
            }
            if(1 == pay.getMixpay()){
                order.setMixpay(1);
                res = payinfoService.savedomixpay(req,order);
            }else{
                res = payinfoService.savedopay(req,order);
            }
            result.putAll(res);
            return result;
        } catch (Exception e) {
            log.error("支付异常", e);
            if(e instanceof ZHHQException){
                ZHHQException ze = (ZHHQException)e;
                result=R.error(ze.getErrCode(), ze.getMsg());
                return result;
            }else{
                return R.error();
            }
        }
    }
    
    /**ali 支付回调
     * @param request
     * @param response
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @IgnoreSign
    @IgnoreToken
    @RequestMapping(value = "alipayreceive")
    @ResponseBody
    public void alipayreceive(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "--获取支付数据");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("支付宝返回参数 ："+params.toString());
        log.info("开始验证");
        if(NewAliPayUtils.checkSign(params)){
            log.info("验证通过"); 
            try {
                int types = 1;// 支付宝
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8"); // 交易状态
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8"); // 支付宝交易号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8"); // 支付宝交易号
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"); // 支付宝交易号
                
                JSONObject param = new JSONObject();
                param.put("orderCode", out_trade_no);
                param.put("paytype", types);
                if("TRADE_SUCCESS".equalsIgnoreCase(trade_status) ){
                        payinfoService.savepayinfo(payinfoService.createpayinfo(trade_no,out_trade_no,types,total_amount));
                        log.info("支付宝支付，交易成功");
                        response.getWriter().write("success");
                }else{
                    log.info("支付宝出错");
                    response.getWriter().write("fail");
                }
            } catch (Exception e) {
                response.getWriter().write("fail");
                log.info("支付宝出错");
            }
        }
    }
        
      

        /**
         * 微信回调
         * 
         * @param request
         * @param response
         * @throws Exception
         */
       
        @RequestMapping(value = "wechatreceive")
        @IgnoreSign
        @IgnoreToken
        @ResponseBody
        public void wechatreceive(HttpServletRequest request, HttpServletResponse response) throws Exception {
            ResponseHandler resHandler = new ResponseHandler(request, response);
            try {
                log.info("微信支付==进入==");
                InputStream inStream = request.getInputStream();
                ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                resHandler.setKey(ConstantUtil.APP_KEY);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inStream.read(buffer)) != -1) {
                    outSteam.write(buffer, 0, len);
                }
                response.setHeader("ContentType", "text/xml");
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                outSteam.close();
                inStream.close();
 
                String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
                log.info("微信支付==result==" + result);
                Map<Object, Object> map = XMLUtil.doXMLParse(result);
                log.info("微信支付==map==" + map);

                String result_code = "";
                String out_trade_no = "";
                String trade_no = "";
                String total_fee ="";
                for (Object keyValue : map.keySet()) {
                    result_code = map.get("result_code").toString();
                    out_trade_no = map.get("out_trade_no").toString();
                    trade_no = map.get("transaction_id").toString();
                    total_fee = map.get("total_fee").toString();
                }
                // 判断
                if(!WXPayUtil.isSignatureValid(result,ConstantUtil.APP_KEY)){
                    log.info("微信验签失败");
                    resHandler.sendToCFT("FAIL");
                }
                int types = 2;
                JSONObject param = new JSONObject();
                param.put("orderCode", out_trade_no);
                param.put("paytype", types);
                if ("SUCCESS".equals(result_code) ) {
                    payinfoService.savepayinfo(payinfoService.createpayinfo(trade_no,out_trade_no,types,total_fee));
                    log.info("微信支付，交易成功");
                    resHandler.sendToCFT("SUCCESS");
                   
                } else {
                    log.info("微信支付失败");
                    resHandler.sendToCFT("FAIL");
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    resHandler.sendToCFT("FAIL");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        /**
         * 退款
         * @param biz
         * @return
         */
        @RequestMapping("/refund")
        @ResponseBody
        public R refund(HttpServletRequest req){
           String biz = req.getParameter("biz"); 
           log.debug(biz);
           R result = new R();
           RefundinfoEntity refundVo = FastJSONUtils.getObject(biz, RefundinfoEntity.class);
//           refundVo.getOut_trade_no();
           String ordercode = refundVo.getOrdercode();
           IOrderService orderservice = orderServiceFactory.getService(refundVo.getOrdertype());
           try {
	        	 //先保存退单号
	       		String refundOrderCode = OrderCodeGenerator.createRefundOrderCode(refundVo.getOrdertype());
	       		refundVo.setCreatetime(new Date());
	       		refundVo.setRefundordercode(refundOrderCode);
	       		refundVo.setStatus(0);
	       		RefundinfoEntity refundInfo = orderservice.getRefundInfo(refundVo);
                R r = refundinfoService.savedorefund(refundInfo);
                return r;
            } catch (Exception e) {
                log.error("退款异常", e);
                if(e instanceof ZHHQException){
                    ZHHQException ze = (ZHHQException)e;
                    result=R.error(ze.getErrCode(), ze.getMsg());
                    return result;
                }else{
                    return R.error();
                }
            }
        } 
}
