package com.lcworld.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.RefundinfoDao;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.factory.PurchaseCountServiceFactory;
import com.lcworld.service.IOrderService;
import com.lcworld.service.IPurchaseCountService;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.service.PayinfoService;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.RefundinfoService;
import com.lcworld.service.UserPurchaseLimitService;
import com.lcworld.service.UserWalletService;
import com.lcworld.utils.R;
import com.lcworld.utils.alipay.NewAliPayUtils;
import com.lcworld.utils.alipay.PayRefundVo;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.utils.wxpay.refundService.Refund;
import com.lcworld.vo.PurchaseCountVo;



@Service("refundinfoService")
public class RefundinfoServiceImpl implements RefundinfoService {
	private Logger log = LoggerFactory.getLogger(RefundinfoServiceImpl.class);
	@Autowired
	private RefundinfoDao refundinfoDao;
	@Autowired
    private OrderServiceFactory orderServiceFactory;
	@Autowired
    private PurchaseCountServiceFactory purchaseCountServiceFactory;
	@Autowired
    private UserWalletService userWalletService;
    @Autowired
    private PurchaseAccountService purchaseAccountService;
    @Autowired
    private OfficePurchaseCountService officePurchaseCountService;
    @Autowired
    private PayinfoService payinfoService;
    @Autowired
    private UserPurchaseLimitService userPurchaseLimitService;
	@Override
	public RefundinfoEntity queryObject(Integer id){
		return refundinfoDao.queryObject(id);
	}
	
	@Override
	public List<RefundinfoEntity> queryList(Map<String, Object> map){
		return refundinfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return refundinfoDao.queryTotal(map);
	}
	
	@Override
	public void save(RefundinfoEntity refundinfo){
		refundinfoDao.save(refundinfo);
	}
	
	@Override
	public void update(RefundinfoEntity refundinfo){
		refundinfoDao.update(refundinfo);
	}
	
	@Override
	public void delete(Integer id){
		refundinfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		refundinfoDao.deleteBatch(ids);
	}

	@Override
	public R savedorefund(RefundinfoEntity refund){
		Map<String, Object> map = new HashMap<>();
   		map.put("orderCode", refund.getOrdercode());
   		List<PayinfoEntity> list = payinfoService.queryList(map);
   		if (ValidateUtil.isValid(list)) {
			PayinfoEntity payinfo = list.get(0);
			refund.setTransactioncode(payinfo.getTransactionId());
		}
		//保存退款信息
		save(refund);
		R r = new R();
		switch (refund.getRefundtype().intValue()) { 
        case 1:
            r = alirefund(refund);
            break;
        case 2:
            r = wxrefund(refund);
            break;
        case 3:
            r = personalwalletRefund(refund);
            break;// 个人钱包
        case 4:
            r = groupwalletRefund(refund);
            break;// 集体钱包
        case 5:
        	r = personalvoucherRefund(refund);
            break;// 个人代金券
        default:
        	throw new ZHHQException(1501, "不存在该退款方式");
        }
		int err = (int)r.get("errCode");
		if (err==0) {
			refund.setStatus(1);
			update(refund);
			//退款成功更改订单状态
			IOrderService service = orderServiceFactory.getService(refund.getOrdertype());
			service.saveRefund(refund);
		}else{
			refund.setStatus(2);
			update(refund);
		}
		return r;
	}

	private R personalvoucherRefund(RefundinfoEntity refund) {
		// TODO Auto-generated method stub
		return R.error(1,"不能退还");
	}

	private R groupwalletRefund(RefundinfoEntity refund) {
		Integer uid = refund.getUid();
        JSONObject obj = new JSONObject();
        obj.put("uid", uid);
        obj.put("servicecode", refund.getOrdertype());
        IPurchaseCountService service = purchaseCountServiceFactory.getPurchaseCountService(refund.getOrdertype());
        PurchaseCountVo vo = service.getPurchaseCountVo(obj);
        userPurchaseLimitService.savePurchaselimit(uid,refund.getRefundmoney(),refund.getOrdertype(), 2);
        service.savePurchaseCountRefund(vo);
        return R.ok();
	}

	private R personalwalletRefund(RefundinfoEntity refund) {
		Integer uid = refund.getUid();
        JSONObject param = new JSONObject();
        param.put("uid", uid);
        List<UserWalletEntity> wallertlist = userWalletService.queryList(param);
        UserWalletEntity wallet = wallertlist.get(0);
        BigDecimal sub = wallet.getRemain().add(refund.getRefundmoney());
        wallet.setRemain(sub);
        userWalletService.update(wallet);
        return R.ok();
	}

	private R wxrefund(RefundinfoEntity refund) {
		BigDecimal totalMoney = refund.getTotalrefundmoney();
		BigDecimal refundMoney = refund.getRefundmoney();
		BigDecimal totalMoneyFen = totalMoney.multiply(new BigDecimal(100));
		BigDecimal refundMoneyFen = totalMoney.multiply(new BigDecimal(100));
		Map<String, Object> info = Refund.wechatRefund(refund.getRefundordercode(), refund.getOrdercode(),String.valueOf(totalMoneyFen.intValue()), String.valueOf(refundMoneyFen.intValue()));
		String status = (String)info.get("return_code");
		if (status.equalsIgnoreCase("SUCCESS")) {
			return R.ok();
		}else{
			return R.error(1,"微信退款失败").put("info", info);
		}
	}
	//阿里退款
	private R alirefund(RefundinfoEntity refund){
		PayRefundVo alirefund = new PayRefundVo();
		BigDecimal totalMoney = refund.getTotalrefundmoney();
		BigDecimal refundMoney = refund.getRefundmoney();
		BigDecimal totalMoneyFen = totalMoney.multiply(new BigDecimal(100));
		BigDecimal refundMoneyFen = totalMoney.multiply(new BigDecimal(100));
		alirefund.setTrade_no(refund.getTransactioncode());
		alirefund.setOut_trade_no(refund.getTransactioncode());
		alirefund.setTotal_amount(String.valueOf(totalMoneyFen.intValue()/100.0));
		alirefund.setRefund_amount(String.valueOf(refundMoneyFen.intValue()/100.0));
		alirefund.setOut_request_no(refund.getRefundordercode());
		PayRefundVo info = new PayRefundVo();
		try {
			info = NewAliPayUtils.refund(alirefund);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("阿里退款信息:"+JSONObject.toJSON(info));
		String status = info.getRefund_status();
		if (status.equalsIgnoreCase("SCUESS")) {
			return R.ok();
		}else{
			return R.error(1,"阿里退款失败").put("info", info);
		}
	}
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(12.33);
		System.out.println(String.valueOf(bigDecimal.intValue()));
	}
}
