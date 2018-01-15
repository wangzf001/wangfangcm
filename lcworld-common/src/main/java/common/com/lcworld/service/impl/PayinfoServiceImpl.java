package com.lcworld.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.dao.TbMessageOrderWebDao;
import com.lcworld.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.PayinfoDao;
import com.lcworld.exception.ZHHQException;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.factory.PurchaseCountServiceFactory;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.IOrderService;
import com.lcworld.service.IPurchaseCountService;
import com.lcworld.service.LffwOrderService;
import com.lcworld.service.LffwVoucherPriceService;
import com.lcworld.service.LffwVoucherService;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.service.PayinfoService;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.UserPurchaseLimitService;
import com.lcworld.service.UserWalletService;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.utils.alipay.AliPayPayVo;
import com.lcworld.utils.alipay.NewAliPayUtils;
import com.lcworld.utils.service.WXPay;
import com.lcworld.utils.wxutil.ConstantUtil;
import com.lcworld.utils.wxutil.WXPrepay;
import com.lcworld.vo.PayOrderVo;
import com.lcworld.vo.PurchaseCountVo;

@Service("payinfoService")
public class PayinfoServiceImpl implements PayinfoService {
    private Logger log = LoggerFactory.getLogger(PayinfoService.class);
    @Autowired
    private PayinfoDao payinfoDao;
    @Autowired
    private OrderServiceFactory orderServiceFactory;
    @Autowired
    private PurchaseCountServiceFactory purchaseCountServiceFactory;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private PurchaseAccountService purchaseAccountService;
    @Autowired
    private LffwVoucherService lffwVoucherService;
    @Autowired
    private BaseUserRoleService baseUserRoleService;
    @Autowired
    private DeparpurchaseCountService deparpurchaseCountService;
    @Autowired
    private OfficePurchaseCountService officePurchaseCountService;
    @Autowired
    private UserPurchaseLimitService userPurchaseLimitService;
    @Autowired
    private LffwVoucherPriceService lffwVoucherPriceService;
    @Autowired
    private TbMessageOrderWebDao messageOrderWebDao;

    @Override
    public PayinfoEntity queryObject(Integer id) {
        return payinfoDao.queryObject(id);
    }

    @Override
    public List<PayinfoEntity> queryList(Map<String, Object> map) {
        return payinfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return payinfoDao.queryTotal(map);
    }

    @Override
    public void save(PayinfoEntity payinfo) {
        createMessageOrderWeb(payinfo);
        payinfoDao.save(payinfo);
    }

    @Override
    public void update(PayinfoEntity payinfo) {
        payinfoDao.update(payinfo);
    }

    @Override
    public void delete(Integer id) {
        payinfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        payinfoDao.deleteBatch(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lcworld.service.PayinfoService#savepayinfo(com.lcworld.entity.
     * PayinfoEntity)
     */
    @Override
    public void savepayinfo(PayinfoEntity pay) throws Exception {
    	// 理发券单价
        BigDecimal voucherprice =  lffwVoucherPriceService.queryList(new HashMap<String,Object>()).get(0).getVoucherprice();
        
        // 解析订单类型，格式为  0+orderType
        pay.setOrdertype(getordertypeByOrderCode(pay));
        
        // 获取对应业务service
        IOrderService orderService = orderServiceFactory.getService(pay.getOrdertype());
        
        // 校验数据库订单
        PayOrderVo ordervo = orderService.getOrderVo(createOrderVo(pay));
        ordervo.setPaytype(pay.getPaytype());
        
        //设置是否混合支付
        if(null == pay.getMixpay() ){
            //回调
            ordervo.setMixpay(ordervo.getMixpay());
        }else{
            //wallet
            ordervo.setMixpay(pay.getMixpay());
            ordervo.setPrepayvouchercount(pay.getLockcount());
        }
        if(ordervo.getMixpay()!=null&&1 == ordervo.getMixpay()){
            ordervo.setPaymoney(ordervo.getPaymoney().subtract(voucherprice.multiply(new BigDecimal(ordervo.getPrepayvouchercount()))));
        }
        
        // 保存支付信息
        savepayinfo(pay, ordervo);
        
        // 更新对应订单
        orderService.savePayed(ordervo);
        
        //deal mix pay
        ordervo.setPrepaytype(5);
        ordervo.setPrepaymoney(voucherprice.multiply(new BigDecimal(ordervo.getPrepayvouchercount())));
        dealmixpay(ordervo);
    }

    @Override
    public void saveWalletPayed(UserWalletEntity wallet, PayinfoEntity pay) throws Exception {
        savepayinfo(pay);
        userWalletService.saveWalletPayed(wallet, pay);
    }

    @Override
    public void savePurchasePayed(PurchaseAccountEntity account, PayinfoEntity pay) throws Exception {
        savepayinfo(pay);
        purchaseAccountService.savePurchasePayed(account, pay);
    }

    @Override
    public void saveVoucherPayed(LffwVoucherEntity voucher, PayinfoEntity pay) throws Exception {
        savepayinfo(pay);
        lffwVoucherService.saveVoucherPayed(voucher, pay);

    }

    /*
     * (non-Javadoc)
     *  ice.PayinfoService#savedopay(javax.servlet.http.
     * HttpServletRequest, com.lcworld.vo.PayOrderVo)
     */
    public JSONObject savedopay(HttpServletRequest req, PayOrderVo order) throws Exception {
        JSONObject res = new JSONObject();
        res.put("errCode", 0);
        switch (order.getPaytype()) { 
        case 1:
            alipay(req, order, res);
            break;
        case 2:
            wxpay(req, order, res);
            break;
        case 3:
            personalwalletPay(order, res);
            break;// 个人钱包
        case 4:
            groupwalletPay(order, res); 
            break;// 集体钱包
        case 5:
            personalvoucherPay(order, res);
            break;// 个人代金券
        default:
            setpayTypeExceptionResult(res);
        }
        return res;
    }

    @Override
    public PayinfoEntity createpayinfo(String trade_no, String out_trade_no, int types, String total_fee) {
        PayinfoEntity pay = new PayinfoEntity();
        pay.setCreatetime(new Date());
        pay.setTransactionId(trade_no);
        pay.setPaytype(types);
        pay.setPaytime(new Date());
        pay.setStatus(1);
        pay.setOrdercode(out_trade_no);
        pay.setPaymoney(new BigDecimal(total_fee));
        return pay;
    }

    /**
     * 微信
     * 
     * @param request
     * @param order
     * @param res
     */
    private void wxpay(HttpServletRequest request, PayOrderVo order, JSONObject res) {
        // 通知的URL
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        String notifyUrl = basePath + "appuser/pay/wechatreceive";
        String prepayid = getWXPrePayId(request, order.getOrdercode(), order.getPaymoney(), order.getOrdertype(),
                notifyUrl);
        if (prepayid != null && prepayid.length() > 10) {
            // 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
            String jsParam = WXPay.createPackageValue(ConstantUtil.APP_ID, ConstantUtil.PARTNER_KEY, prepayid);
            log.info("生成的微信调起参数为：" + jsParam);
            // 此处可以添加订单的处理逻辑
            JSONObject params = JSONObject.parseObject(jsParam);
            res.putAll(params);
        }
        res.put("prepayid", prepayid);
    }

    /**
     * ali 支付
     * 
     * @param req
     * @param order
     * @param res
     */
    private void alipay(HttpServletRequest req, PayOrderVo order, JSONObject res) {
        String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath() + "/";
        AliPayPayVo vo = createPayVo(basePath, order.getOrdercode(), order.getPaymoney(), order.getOrdertype());
        String payLink = NewAliPayUtils.pay(vo);
        log.debug("payLink -- " + payLink);
        res.put("payLink", payLink);
    }

    /**
     * 生成阿里支付参数
     * 
     * @param basePath
     * @param order
     * @return
     */
    private AliPayPayVo createPayVo(String basePath, String orderCode, BigDecimal actualPrice, Integer orderType) {
        String notifyUrl = basePath + "appuser/pay/alipayreceive";
        String item = APPConstant.gettypeMap().get(orderType);
        String payMoney = new DecimalFormat("######0.00").format(actualPrice);
        payMoney = "0.01";
        AliPayPayVo vo = new AliPayPayVo();
        vo.setBody(item);
        vo.setNotifyUrl(notifyUrl);
        vo.setOutTradeNo(orderCode);
        vo.setSubject(item);
        vo.setTotalAmount(payMoney);
        return vo;
    }

    private void setpayTypeExceptionResult(JSONObject res) {
        throw new ZHHQException(1501, "该支付方式尚未开通");

    }

    private void personalvoucherPay(PayOrderVo order, JSONObject res) throws Exception {
        Integer curuid = order.getCurUid();
        JSONObject param = new JSONObject();
        param.put("uid", curuid);
        param.put("orderCode", order.getOrdercode());
        param.put("paytype", order.getPaytype());
        List<LffwVoucherEntity> voucherlist = null;
        voucherlist = lffwVoucherService.queryList(param);
        if (!ValidateUtil.isValid(voucherlist)) {
            throw new ZHHQException(1507, "您没有代金券账号");
        }
        LffwVoucherEntity voucher = voucherlist.get(0);
        List<UserWalletEntity> wallertlist = userWalletService.queryList(param);
        UserWalletEntity wallet = wallertlist.get(0);
        if (validPass(order, wallet.getPaypass(), res)) {
            int sub = voucher.getRemain() - order.getVouchercount();
            if (sub >= 0) {
                voucher.setRemain(sub);
                PayinfoEntity pay = createpayinfo("", order.getOrdercode(), order.getOrdertype(),
                        String.valueOf(order.getPaymoney()));
                pay.setPaytype(order.getPaytype());
                pay.setPaytime(new Date());
                pay.setStatus(1);
                saveVoucherPayed(voucher, pay);
            } else {
                throw new ZHHQException(1503, "可用余额不足");
            }
        }

    }

    private void groupwalletPay(PayOrderVo order, JSONObject res) throws Exception {
        Integer curuid = order.getCurUid();
        JSONObject obj = new JSONObject();
        obj.put("uid", curuid);
        obj.put("servicecode", order.getOrdertype());
        IPurchaseCountService service = purchaseCountServiceFactory.getPurchaseCountService(order.getOrdertype());
//        PurchaseCountVo vo = service.getPurchaseCountVo(obj);
//        vo.setOrdervo(order);
//        validPass(vo.getOrdervo(),vo.getPaypass(),null);
        userPurchaseLimitService.savePurchaselimit(order.getCurUid(),order.getPaymoney(),order.getOrdertype(), 1);
//        service.savePurchaseCount(vo);
        PayinfoEntity pay = createPayInfo(order);
        savepayinfo(pay);
    }




   

    private PayinfoEntity createPayInfo(PayOrderVo order) {
        PayinfoEntity pay = createpayinfo("", order.getOrdercode(), order.getOrdertype(),
                String.valueOf(order.getPaymoney()));
        pay.setPaytype(order.getPaytype());
        pay.setPaytime(new Date());
        pay.setStatus(1);
        return pay;
    }

    /**
     * 校验密码
     * 
     * @param order
     * @param accountpass
     * @param res
     * @return
     */
    private boolean validPass(PayOrderVo order, String accountpass, JSONObject res) {
        if (!ValidateUtil.isValid(accountpass)) {
            throw new ZHHQException(1507, "您的账号还没有设置密码");
        }
        if (!accountpass.equalsIgnoreCase(order.getPaypass())) {
            throw new ZHHQException(1504, "支付密码错误");
        }

        return true;
    }

   
  

    private void personalwalletPay(PayOrderVo order, JSONObject res) throws Exception {
        Integer curuid = order.getCurUid();
        JSONObject param = new JSONObject();
        param.put("uid", curuid);
        param.put("orderCode", order.getOrdercode());
        param.put("paytype", order.getPaytype());
        List<UserWalletEntity> wallertlist = userWalletService.queryList(param);
        UserWalletEntity wallet = wallertlist.get(0);
        if (validPass(order, wallet.getPaypass(), res)) {
            BigDecimal sub = wallet.getRemain().subtract(order.getPaymoney());
            if (sub.doubleValue() >= 0) {
                wallet.setRemain(sub);
                PayinfoEntity pay = createpayinfo("", order.getOrdercode(), order.getOrdertype(), String.valueOf(order.getPaymoney()),order.getMixpay(),order.getPrepayvouchercount());
                pay.setOrdertype(order.getOrdertype());
                pay.setPaytype(order.getPaytype());
                pay.setPaytime(new Date());
                pay.setStatus(1);
                saveWalletPayed(wallet, pay);
            } else {
                throw new ZHHQException(1503, "可用余额不足");
            }
        }

    }

    private PayinfoEntity createpayinfo(String trade_no, String ordercode, Integer ordertype, String total_amount,
            Integer mixpay, Integer prepayvouchercount) {
        PayinfoEntity pay = createpayinfo(trade_no,ordercode,ordertype,total_amount);
        pay.setMixpay(mixpay);
        pay.setLockcount(prepayvouchercount);
        return pay;
    }

    /**释放混合支付中的锁定代金券
     * @param order
     * @throws Exception 
     */
    private void dealmixpay(PayOrderVo order) throws Exception {
        if(null != order.getMixpay() && 1 == order.getMixpay()){
            LffwVoucherEntity voucher = savefreevoucher(order);
            PayinfoEntity pay = savefreepayinfo(order);
            lffwVoucherService.saveVoucherPayed(voucher, pay);
        }
        
    }

    /**保存释放代金券支付信息
     * @param order
     * @return
     * @throws Exception
     */
    private PayinfoEntity savefreepayinfo(PayOrderVo order) throws Exception {
        BigDecimal voucherprice =  lffwVoucherPriceService.queryList(new HashMap<String,Object>()).get(0).getVoucherprice();
        PayinfoEntity pay = createpayinfo("", order.getOrdercode(), order.getOrdertype(),
                String.valueOf(order.getPrepaymoney()));
        pay.setPaytype(order.getPrepaytype());//代金券
        pay.setVouchercount(order.getPrepayvouchercount());
        pay.setVoucherprice(voucherprice);
        pay.setPaytime(new Date());
        pay.setStatus(1);
        
        pay.setOrdertype(getordertypeByOrderCode(pay));
        order.setPaytype(order.getPrepaytype());
        order.setPaymoney(order.getPrepaymoney());
        order.setVouchercount(order.getPrepayvouchercount());
        // save pay 
        savepayinfo(pay, order);
        return pay;
        
    }

    /**保存释放代金券
     * @param order
     * @return
     */
    private LffwVoucherEntity savefreevoucher(PayOrderVo order) {
        Integer curuid = order.getCurUid();
        JSONObject param = new JSONObject();
        param.put("uid", curuid);
        param.put("orderCode", order.getOrdercode());
        param.put("paytype", order.getPaytype());
        List<LffwVoucherEntity> voucherlist = null;
        voucherlist = lffwVoucherService.queryList(param);
        LffwVoucherEntity voucher = voucherlist.get(0);
        voucher.setRemain(voucher.getRemain()-order.getPrepayvouchercount());
        voucher.setLockcount(voucher.getLockcount()-order.getPrepayvouchercount());
        lffwVoucherService.update(voucher);
        return voucher;
        
    }

    /**
     * 创建微信prepayid
     * 
     * @param order
     * @param notifyUrl
     * @return
     */
    private String getWXPrePayId(HttpServletRequest request, String orderCode, BigDecimal actualPrice,
            Integer orderType, String notifyUrl) {
        String payMoney = String.valueOf(new BigDecimal(actualPrice.doubleValue() * 100).intValue());
        String item = APPConstant.gettypeMap().get(orderType);
        // 写死0.01元
        payMoney = "1";
        String remoteIPAddress = request.getRemoteAddr();
         //remoteIPAddress="123.57.80.58";
        // 商户相关资料
        String appid = ConstantUtil.APP_ID;
        // String appsecret = ConstantUtil.APP_SECRET;
        String partner = ConstantUtil.PARTNER;
        String partnerkey = ConstantUtil.PARTNER_KEY;

        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(appid);
        prePay.setBody(item);
        prePay.setPartnerKey(partnerkey);
        prePay.setMch_id(partner);
        prePay.setNotify_url(notifyUrl);
        prePay.setOut_trade_no(orderCode);
        prePay.setSpbill_create_ip(remoteIPAddress);
        prePay.setTotal_fee(payMoney);
        prePay.setTrade_type("APP");

        String prepayid = prePay.submitXmlGetPrepayId();
        log.info("获取的预支付订单是：" + prepayid);
        return prepayid;
    }

    private void savepayinfo(PayinfoEntity pay, PayOrderVo ordervo) {
        pay.setCreatetime(new Date());
        pay.setOrdercode(ordervo.getOrdercode());
        pay.setOrdertype(ordervo.getOrdertype());
        pay.setOrderid(ordervo.getOrderid());
        pay.setPaytype(ordervo.getPaytype());
        pay.setStatus(1);
        pay.setUid(ordervo.getUid());
        pay.setVouchercount(ordervo.getVouchercount());
        pay.setPaymoney(ordervo.getPaymoney());
        save(pay);

    }

    private Integer getordertypeByOrderCode(PayinfoEntity pay) {
        return Integer.parseInt(pay.getOrdercode().substring(0, 3));
    }

    private PayOrderVo createOrderVo(PayinfoEntity pay) {
        PayOrderVo vo = new PayOrderVo();
        vo.setOrdercode(pay.getOrdercode());
        vo.setOrderid(pay.getOrderid());
        vo.setOrdertype(pay.getOrdertype());
        vo.setPaytype(pay.getPaytype());
        return vo;
    }

    /**保存混合支付
     * @param req
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject savedomixpay(HttpServletRequest req, PayOrderVo order) throws Exception {
       dealvoucher(order);
       setordermixpay(order);
       return dealotherpay(req,order);
      
    }

    private JSONObject dealotherpay(HttpServletRequest req,PayOrderVo order) throws Exception {
        order.setPrepaymoney(order.getPaymoney());
        order.setPrepaytype(order.getPaytype());
        order.setPrepayvouchercount(order.getVouchercount());
        order.setPaytype(order.getOtherpay().getPaytype());
        order.setPaymoney(order.getOtherpay().getPaymoney());
        order.setVouchercount(0);
        return  savedopay(req,order);
    }

    private void setordermixpay(PayOrderVo order) {
      //update order mix
        IOrderService orderService = orderServiceFactory.getService(order.getOrdertype());
        LffwOrderService lffwOrderService = (LffwOrderService) orderService;
        lffwOrderService.savemixpay(order);
    }

    /**锁定代金券
     * @param order
     */
    private void dealvoucher(PayOrderVo order) {
        Integer curuid = order.getCurUid();
        JSONObject param = new JSONObject();
        param.put("uid", curuid);
        param.put("orderCode", order.getOrdercode());
        param.put("paytype", order.getPaytype());
        List<LffwVoucherEntity> voucherlist = null;
        voucherlist = lffwVoucherService.queryList(param);
        if (!ValidateUtil.isValid(voucherlist)) {
            throw new ZHHQException(1507, "您没有代金券账号");
        }
        LffwVoucherEntity voucher = voucherlist.get(0);
        int sub = voucher.getRemain() - order.getVouchercount();
        if (sub >= 0) {
            //lock voucer
            int lcount = voucher.getLockcount()==null?0:voucher.getLockcount();
            voucher.setLockcount(lcount+order.getVouchercount());
            lffwVoucherService.update(voucher);
        } else {
            throw new ZHHQException(1503, "可用余额不足");
        }
        
    }

    /**
     * 添加到后台消息列表
     * @param payinfo
     */
    private void createMessageOrderWeb(PayinfoEntity payinfo){
        log.debug("payInfo 详情"+JSONObject.toJSONString(payinfo));
        TbMessageOrderWebEntity orderWebEntity = new TbMessageOrderWebEntity();
        orderWebEntity.setOrderCode(payinfo.getOrdercode());
        orderWebEntity.setUid(payinfo.getUid());
        orderWebEntity.setServerTypeId(payinfo.getOrdertype());
        orderWebEntity.setMessageTitle("用户下单");
        orderWebEntity.setMessageContent("用户已成功下单,请注意查收！");
        orderWebEntity.setCreateTime(new Date());
        orderWebEntity.setIsDelete(0);
        orderWebEntity.setIsRead(0);
        log.debug("orderWebEntity 详情"+JSONObject.toJSONString(orderWebEntity));
        messageOrderWebDao.save(orderWebEntity);
    }
}
