package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.UserDepositDao;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.PurchaseAccountEntity;
import com.lcworld.entity.UserDepositEntity;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.UserDepositService;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



@Service("userDepositService")
public class UserDepositServiceImpl implements UserDepositService {
	@Autowired
	private UserDepositDao userDepositDao;
	@Autowired
    private PurchaseAccountService purchaseAccountService;
	@Override
	public UserDepositEntity queryObject(Integer id){
		return userDepositDao.queryObject(id);
	}
	
	@Override
	public List<UserDepositEntity> queryList(Map<String, Object> map){
		return userDepositDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDepositDao.queryTotal(map);
	}
	
	@Override
	public void save(UserDepositEntity userDeposit){
		userDepositDao.save(userDeposit);
	}
	
	@Override
	public void update(UserDepositEntity userDeposit){
		userDepositDao.update(userDeposit);
	}
	
	@Override
	public void delete(Integer id){
		userDepositDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userDepositDao.deleteBatch(ids);
	}

	@Override
	public UserDepositEntity queryByCondition(Map<String, Object> params) {
		return userDepositDao.queryByCondition(params);
	}

	@Override
	public R deleteUserDeposit(JSONObject params) {
		//查询用户押金
		UserDepositEntity deposit = queryByCondition(params);
		if (ValidateUtil.isValid(deposit)) {
			//删除用户押金
			delete(deposit.getId());
			//退还金额
			if (params.getInteger("type")==APPConstant.TYPE_DEPOSIT_DS) {
				//退还到对公账户
				List<PurchaseDTO> list = purchaseAccountService.getPubPurchaseList(deposit.getUid(), String.valueOf(APPConstant.TYPE_DSFW));
				if (ValidateUtil.isValid(list)) {
					PurchaseDTO purchaseDTO = list.get(0);
					PurchaseAccountEntity account = new PurchaseAccountEntity();
					account.setId(purchaseDTO.getId());
					account.setRemain(deposit.getDeposit().add(purchaseDTO.getMoney()));
					purchaseAccountService.update(account);
				}
			}
		}
		return null;
	}
	
}
