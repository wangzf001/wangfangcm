package com.lcworld.vo;

import java.math.BigDecimal;
import java.util.List;

public class PayOrderVo {

    private String paypass;
    //支付用户uid
    private Integer curUid;
    //订单uid
    private Integer uid ;
    private Integer orderid;
    //支付类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
    private Integer paytype;
    //订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
    private Integer ordertype;
    //订单状态 
    private Integer status;
    //支付金额
    private BigDecimal paymoney;
    //订单号
    private String ordercode;
    //代金券数量
    private Integer vouchercount;
    //每张代金券金额
    private BigDecimal voucherprice;
    //1:医疗挂号
    private Integer substatus;
    //支付状态
    private Integer paystatus;
    //其他支付方式
    private PayOrderVo otherpay;
    
    
    // 1: 是混合支付，0：否
    private Integer mixpay;
    private Integer prepaytype;
    private BigDecimal prepaymoney;
    private Integer prepayvouchercount = 0;
    
    
    
    
    
    
   
  
    public Integer getPrepayvouchercount() {
        return prepayvouchercount;
    }
    public void setPrepayvouchercount(Integer prepayvouchercount) {
        this.prepayvouchercount = prepayvouchercount;
    }
    public BigDecimal getPrepaymoney() {
        return prepaymoney;
    }
    public void setPrepaymoney(BigDecimal prepaymoney) {
        this.prepaymoney = prepaymoney;
    }
    public Integer getPrepaytype() {
        return prepaytype;
    }
    public void setPrepaytype(Integer prepaytype) {
        this.prepaytype = prepaytype;
    }
   
    public Integer getMixpay() {
        return mixpay;
    }
    public void setMixpay(Integer mixpay) {
        this.mixpay = mixpay;
    }
    public PayOrderVo getOtherpay() {
        return otherpay;
    }
    public void setOtherpay(PayOrderVo otherpay) {
        this.otherpay = otherpay;
    }
    public String getPaypass() {
        return paypass;
    }
    public void setPaypass(String paypass) {
        this.paypass = paypass;
    }
    public Integer getCurUid() {
        return curUid;
    }
    public void setCurUid(Integer curUid) {
        this.curUid = curUid;
    }
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getPaystatus() {
        return paystatus;
    }
    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }
    public void setSubstatus(Integer substatus) {
        this.substatus = substatus;
    }
    public int getSubstatus() {
        return substatus;
    }
    public void setSubstatus(int substatus) {
        this.substatus = substatus;
    }
    public Integer getOrderid() {
        return orderid;
    }
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
    public Integer getPaytype() {
        return paytype;
    }
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }
    public Integer getOrdertype() {
        return ordertype;
    }
    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public BigDecimal getPaymoney() {
        return paymoney;
    }
    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }
    public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public Integer getVouchercount() {
        return vouchercount;
    }
    public void setVouchercount(Integer vouchercount) {
        this.vouchercount = vouchercount;
    }
    public BigDecimal getVoucherprice() {
        return voucherprice;
    }
    public void setVoucherprice(BigDecimal voucherprice) {
        this.voucherprice = voucherprice;
    }
    
}
