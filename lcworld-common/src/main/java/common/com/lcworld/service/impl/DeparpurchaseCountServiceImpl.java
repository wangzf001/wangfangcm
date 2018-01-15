package com.lcworld.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.DeparpurchaseCountDao;
import com.lcworld.dao.UserPurchaseLimitDao;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.DeparpurchaseCountEntity;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.PurchaseAccounlogService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.PurchaseCountVo;

@Service("deparpurchaseCountService")
public class DeparpurchaseCountServiceImpl implements DeparpurchaseCountService {
	@Autowired
	private DeparpurchaseCountDao deparpurchaseCountDao;
	@Autowired
	private PurchaseAccounlogService purchaseAccounlogService;
	@Autowired
	private UserPurchaseLimitDao purchaseLimitDao;

	@Override
	public DeparpurchaseCountEntity queryObject(Integer id) {
		return deparpurchaseCountDao.queryObject(id);
	}

	@Override
	public List<DeparpurchaseCountEntity> queryList(Map<String, Object> map) {
		return deparpurchaseCountDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return deparpurchaseCountDao.queryTotal(map);
	}

	@Override
	public void save(DeparpurchaseCountEntity deparpurchaseCount) {
		deparpurchaseCountDao.save(deparpurchaseCount);
	}

	@Override
	public void update(DeparpurchaseCountEntity deparpurchaseCount) {
		deparpurchaseCountDao.update(deparpurchaseCount);
	}

	@Override
	public void delete(Integer id) {
		deparpurchaseCountDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		deparpurchaseCountDao.deleteBatch(ids);
	}

	@Override
	public List<DeparpurchaseCountEntity> queryDPCList(Query query) {
		return deparpurchaseCountDao.queryDPCList(query);
	}

	@Override
	public int queryDPCTotal(Query query) {
		return deparpurchaseCountDao.queryDPCTotal(query);
	}

	@Override
	public List<DeparpurchaseCountEntity> queryFDPCList(JSONObject obj) {
		return deparpurchaseCountDao.queryFDPCList(obj);
	}

	@Override
	public PurchaseCountVo getPurchaseCountVo(JSONObject obj) {

		PurchaseCountVo vo = new PurchaseCountVo();
		List<DeparpurchaseCountEntity> dpclist = deparpurchaseCountDao
				.queryFDPCList(obj);
		if (ValidateUtil.isValid(dpclist)) {
			if (dpclist.size() > 1) {
				throw new ZHHQException(1506, "该司局该类型服务设置多个账户,请联系管理员进行修正");
			}
			DeparpurchaseCountEntity dpc = dpclist.get(0);
			vo.setId(dpc.getId());
			vo.setRemain(dpc.getRemain());
			vo.setPaypass(dpc.getPaypass());

		} else {
			throw new ZHHQException(1506, "没有对公账户使用权限，不可进行支付");
		}
		return vo;
	}

	@Override
	public void savePurchaseCount(PurchaseCountVo vo) {
		BigDecimal sub = vo.getRemain().subtract(vo.getOrdervo().getPaymoney());
		if (sub.doubleValue() >= 0) {
			DeparpurchaseCountEntity dpc = new DeparpurchaseCountEntity();
			dpc.setId(vo.getId());
			dpc.setRemain(sub);
			update(dpc);
			purchaseAccounlogService
					.savelog(vo.getOrdervo(), dpc.getId(), 1, 1);
		} else {
			throw new ZHHQException(1503, "可用余额不足");
		}
	}

	@Override
	public List<PurchaseDTO> getPubPurchaseList(Integer uid, Integer departid,
			Integer officeid, String servicecode) {
		if (!ValidateUtil.isValidAll(new Object[] { departid, servicecode })) {
			return null;
		}
		JSONObject param = new JSONObject();
		param.put("departid", departid);
		param.put("uid", uid);
		param.put("servicecode", servicecode);
		return deparpurchaseCountDao.getPubPurchaseList(param);
	}

	@Override
	public void savePurchaseCountRefund(PurchaseCountVo vo) {
		if (!ValidateUtil.isValid(vo.getRemain())) {
			vo.setRemain(new BigDecimal("0"));
		}
		BigDecimal sub = vo.getRemain().add(vo.getOrdervo().getPaymoney());
		DeparpurchaseCountEntity dpc = new DeparpurchaseCountEntity();
		dpc.setId(vo.getId());
		dpc.setRemain(sub);
		update(dpc);
		purchaseAccounlogService.savelog(vo.getOrdervo(), dpc.getId(), 2, 1);
	}

	@Override
	public List<UserPurchaseLimitEntity> depart(Map<String, Object> params) {
		Integer purchaseid = Integer.valueOf(params.get("purchaseid").toString());
		// 获取dpc
		DeparpurchaseCountEntity dpc = deparpurchaseCountDao
				.queryObject(purchaseid);
		// 获取已分配人员额度id 列表
		params.put("typeId", dpc.getTypeid());
		List<UserPurchaseLimitEntity> userNoLimitList = purchaseLimitDao.queryUserNoLimitList(new Query(params));
		BigDecimal total = new BigDecimal(0.00);
		for (UserPurchaseLimitEntity upl : userNoLimitList) {
			BigDecimal purchasecount = upl.getHighlimit();
			total.add(purchasecount);
		}
		// 获取额度列表
		if(total.compareTo(dpc.getRemain()) == -1){
			/*dpc.setRemain(dpc.getRemain().subtract(total));
			deparpurchaseCountDao.update(dpc);*/
			return userNoLimitList;
		}
		// 封装额度列表
		return null;

	}

	@Override
	public int departTotal(Query query) {
		// TODO Auto-generated method stub
		return purchaseLimitDao.queryUserNoLimitTotal(query);
	}

}
