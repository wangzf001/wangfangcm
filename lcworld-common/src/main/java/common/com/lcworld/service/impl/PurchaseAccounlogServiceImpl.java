package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.PurchaseAccounlogDao;
import com.lcworld.entity.PurchaseAccounlogEntity;
import com.lcworld.service.PurchaseAccounlogService;
import com.lcworld.vo.PayOrderVo;



@Service("purchaseAccounlogService")
public class PurchaseAccounlogServiceImpl implements PurchaseAccounlogService {
	@Autowired
	private PurchaseAccounlogDao purchaseAccounlogDao;
	
	@Override
	public PurchaseAccounlogEntity queryObject(Integer id){
		return purchaseAccounlogDao.queryObject(id);
	}
	
	@Override
	public List<PurchaseAccounlogEntity> queryList(Map<String, Object> map){
		return purchaseAccounlogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return purchaseAccounlogDao.queryTotal(map);
	}
	
	@Override
	public void save(PurchaseAccounlogEntity purchaseAccounlog){
		purchaseAccounlogDao.save(purchaseAccounlog);
	}
	
	@Override
	public void update(PurchaseAccounlogEntity purchaseAccounlog){
		purchaseAccounlogDao.update(purchaseAccounlog);
	}
	
	@Override
	public void delete(Integer id){
		purchaseAccounlogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		purchaseAccounlogDao.deleteBatch(ids);
	}

    @Override
    public void savelog(PayOrderVo order, Integer dpcid, int usetype, int level) {
        PurchaseAccounlogEntity log = new PurchaseAccounlogEntity();
        log.setCreatetime(new Date());
        log.setAccountid(dpcid);
        log.setLevel(level);
        log.setMoney(order.getPaymoney());
        log.setOrderid(order.getOrderid());
        log.setOrdertype(order.getOrdertype());
        log.setOrdertypename(APPConstant.gettypeMap().get(order.getOrdertype()));
        log.setUid(order.getCurUid());
        log.setUsetype(usetype);//使用类型 2: 收入，1： 支出
        log.setOrdercode(order.getOrdercode());
        purchaseAccounlogDao.save(log);
        
    }
	
}
