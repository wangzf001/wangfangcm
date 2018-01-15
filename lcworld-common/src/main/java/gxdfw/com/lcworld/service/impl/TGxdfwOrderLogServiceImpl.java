package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.TGxdfwOrderDao;
import com.lcworld.dao.TGxdfwOrderLogDao;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.entity.TGxdfwOrderLogEntity;
import com.lcworld.entity.TGxdfwOrderdetailEntity;
import com.lcworld.service.TGxdfwOrderLogService;
import com.lcworld.utils.util.ValidateUtil;



@Service("tGxdfwOrderLogService")
public class TGxdfwOrderLogServiceImpl implements TGxdfwOrderLogService {
	@Autowired
	private TGxdfwOrderLogDao tGxdfwOrderLogDao;
	@Autowired
	private TGxdfwOrderDao tGxdfwOrderDao;
	
	@Override
	public TGxdfwOrderLogEntity queryObject(Integer id){
		return tGxdfwOrderLogDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwOrderLogEntity> queryList(Map<String, Object> map){
		return tGxdfwOrderLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGxdfwOrderLogDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwOrderLogEntity tGxdfwOrderLog){
		tGxdfwOrderLogDao.save(tGxdfwOrderLog);
	}
	
	@Override
	public void update(TGxdfwOrderLogEntity tGxdfwOrderLog){
		TGxdfwOrderEntity order = tGxdfwOrderDao.queryObject(tGxdfwOrderLog.getOrderid());
		
		switch (tGxdfwOrderLog.getStatus().intValue()) {
		case APPConstant.TYPE_GXDFW_LOG_STATUS_WAITSEND:
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
			break;
		case APPConstant.TYPE_GXDFW_LOG_STATUS_WASHING:
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_SERVING);
			break;
		case APPConstant.TYPE_GXDFW_LOG_STATUS_FINISH:
		case APPConstant.TYPE_GXDFW_LOG_STATUS_TOKEN:
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
			break;
		case APPConstant.TYPE_GXDFW_LOG_STATUS_PAYED:
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_EVALUATED);
			order.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_PAYED);
			order.setPayType(APPConstant.TYPE_PAYTYPE_OFFLINE);
			break;
//		case APPConstant.TYPE_GXDFW_LOG_STATUS_EVALUATED:
//			order.setStatus(APPConstant.TYPE_ORDER_STATUS_EVALUATED);
//			break;
		default:
			break;
		}
		tGxdfwOrderDao.update(order);
		tGxdfwOrderLogDao.update(tGxdfwOrderLog);
	}
	
	@Override
	public void delete(Integer id){
		tGxdfwOrderLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGxdfwOrderLogDao.deleteBatch(ids);
	}

	@Override
	public TGxdfwOrderLogEntity queryByOid(Integer oid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("oid", oid);
		List<TGxdfwOrderLogEntity> list = tGxdfwOrderLogDao.queryList(map);
		TGxdfwOrderLogEntity orderLog = new TGxdfwOrderLogEntity();
		if (ValidateUtil.isValid(list)) {
			orderLog = list.get(0);
		}
		return orderLog;
	}

	@Override
	public void updateByOid(TGxdfwOrderLogEntity detailEntity) {
		tGxdfwOrderLogDao.update(detailEntity);
	}
	
}
