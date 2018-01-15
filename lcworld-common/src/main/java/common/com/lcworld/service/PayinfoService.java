package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.PurchaseAccountEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.vo.PayOrderVo;

import java.util.List; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 公用服务-支付信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
public interface PayinfoService {
	
	PayinfoEntity queryObject(Integer id);
	
	List<PayinfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PayinfoEntity payinfo);
	
	void update(PayinfoEntity payinfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savepayinfo(PayinfoEntity pay) throws Exception;

    void saveWalletPayed(UserWalletEntity wallet, PayinfoEntity pay) throws Exception;

    void savePurchasePayed(PurchaseAccountEntity account, PayinfoEntity pay)  throws Exception;

    void saveVoucherPayed(LffwVoucherEntity voucher, PayinfoEntity pay)  throws Exception;

    JSONObject savedopay(HttpServletRequest req, PayOrderVo order) throws Exception;

    PayinfoEntity createpayinfo(String trade_no, String out_trade_no, int types, String total_fee);

    JSONObject savedomixpay(HttpServletRequest req, PayOrderVo order) throws Exception;
}
