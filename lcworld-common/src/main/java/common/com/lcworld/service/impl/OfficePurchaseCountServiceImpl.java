package com.lcworld.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.OfficePurchaseCountDao;
import com.lcworld.dao.PurchaseAccounlogDao;
import com.lcworld.dao.UserPurchaseLimitDao;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.OfficePurchaseCountEntity;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.service.PurchaseAccounlogService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.PurchaseCountVo;

@Service("officePurchaseCountService")
public class OfficePurchaseCountServiceImpl implements
		OfficePurchaseCountService {
	@Autowired
	private OfficePurchaseCountDao officePurchaseCountDao;
	@Autowired
	private PurchaseAccounlogDao purchaseAccounlogDao;
	@Autowired
	private PurchaseAccounlogService purchaseAccounlogService;
	@Autowired
	private UserPurchaseLimitDao purchaseLimitDao;

	@Override
	public OfficePurchaseCountEntity queryObject(Integer id) {
		return officePurchaseCountDao.queryObject(id);
	}

	@Override
	public List<OfficePurchaseCountEntity> queryList(Map<String, Object> map) {
		return officePurchaseCountDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return officePurchaseCountDao.queryTotal(map);
	}

	@Override
	public void save(OfficePurchaseCountEntity officePurchaseCount) {
		officePurchaseCountDao.save(officePurchaseCount);
	}

	@Override
	public void update(OfficePurchaseCountEntity officePurchaseCount) {
		officePurchaseCountDao.update(officePurchaseCount);
	}

	@Override
	public void delete(Integer id) {
		officePurchaseCountDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		officePurchaseCountDao.deleteBatch(ids);
	}

	@Override
	public List<OfficePurchaseCountEntity> queryOPCList(Query query) {
		return officePurchaseCountDao.queryOPCList(query);
	}

	@Override
	public int queryOPCTotal(Query query) {
		return officePurchaseCountDao.queryOPCTotal(query);
	}

	@Override
	public List<OfficePurchaseCountEntity> queryFOPCList(JSONObject obj) {
		return officePurchaseCountDao.queryFOPCList(obj);
	}

	@Override
	public PurchaseCountVo getPurchaseCountVo(JSONObject obj) {
		PurchaseCountVo vo = new PurchaseCountVo();
		List<OfficePurchaseCountEntity> dpclist = officePurchaseCountDao
				.queryFOPCList(obj);
		if (ValidateUtil.isValid(dpclist)) {
			if (dpclist.size() > 1) {
				throw new ZHHQException(1506, "该处室该类型服务设置多个账户,请联系管理员进行修正");
			}
			OfficePurchaseCountEntity dpc = dpclist.get(0);
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
			OfficePurchaseCountEntity dpc = new OfficePurchaseCountEntity();
			dpc.setId(vo.getId());
			dpc.setRemain(sub);
			update(dpc);
			purchaseAccounlogService
					.savelog(vo.getOrdervo(), dpc.getId(), 1, 2);
		} else {
			throw new ZHHQException(1503, "可用余额不足");
		}
	}

	@Override
	public List<PurchaseDTO> getPubPurchaseList(Integer uid, Integer departid,
			Integer officeid, String servicecode) {
		if (!ValidateUtil.isValidAll(new Object[] { officeid, departid,
				servicecode })) {
			return null;
		}
		JSONObject param = new JSONObject();
		param.put("uid", uid);
		param.put("departid", departid);
		param.put("officeid", officeid);
		param.put("servicecode", servicecode);
		return officePurchaseCountDao.getPubPurchaseList(param);
	}

	@Override
	public void savePurchaseCountRefund(PurchaseCountVo vo) {
		if (!ValidateUtil.isValid(vo.getRemain())) {
			vo.setRemain(new BigDecimal("0"));
		}
		BigDecimal sub = vo.getRemain().add(vo.getOrdervo().getPaymoney());
		OfficePurchaseCountEntity dpc = new OfficePurchaseCountEntity();
		dpc.setId(vo.getId());
		dpc.setRemain(sub);
		update(dpc);
		purchaseAccounlogService.savelog(vo.getOrdervo(), dpc.getId(), 2, 2);

	}

	@Override
	public List<UserPurchaseLimitEntity> depart(Map<String, Object> params) {
		// TODO Auto-generated method stub
		// 获取dpc
		Integer purchaseid = Integer.valueOf(params.get("purchaseid").toString());
		OfficePurchaseCountEntity dpc = officePurchaseCountDao
				.queryObject(purchaseid);
		// 获取已分配人员额度id 列表
		params.put("typeId", dpc.getTypeid());
		List<UserPurchaseLimitEntity> userNoLimitList = purchaseLimitDao
				.queryUserNoLimitList(new Query(params));
		BigDecimal total = new BigDecimal(0.00);
		for (UserPurchaseLimitEntity upl : userNoLimitList) {
			BigDecimal purchasecount = upl.getHighlimit();
			total.add(purchasecount);
		}
		// 获取额度列表
		if (total.compareTo(dpc.getRemain()) == -1) {
			/*dpc.setRemain(dpc.getRemain().subtract(total));
			officePurchaseCountDao.update(dpc);*/
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
